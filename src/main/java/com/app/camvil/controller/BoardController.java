package com.app.camvil.controller;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.CampsiteDTO;
import com.app.camvil.dto.ImageDTO;
import com.app.camvil.dto.UserDTO;
import com.app.camvil.dto.requestdto.BoardCreateRequestDTO;
import com.app.camvil.dto.requestdto.BoardDeleteRequestDTO;
import com.app.camvil.dto.requestdto.BoardUpdateRequestDTO;
import com.app.camvil.dto.requestdto.BoardsRequestDTO;
import com.app.camvil.dto.responsedto.BoardCreateResponseDTO;
import com.app.camvil.dto.responsedto.CampsiteCountResponseDTO;
import com.app.camvil.service.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private CampsiteService campsiteService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentService commentService;

    // postman에 url 추가 O
    // search Table 생성 ( search_id, search_content, search_cnt ) (search_cnt 크기 순서로 정렬) O
    // user table user_image_path 추가 (프로필 이미지) => images/users/abcdefg.jpg    O
    // board table, comment table post_date 순으로 재정렬 X 불가능
    // 프로시져 사용
    // paging 처리
    // 게시물 조회, 댓글 상세 조회
    // 마이페이지 : 프로필 사진 변경, 닉네임 변경
    // 로그아웃, 회원탈퇴
    // 파일 경로 resource 접근 지정 src/main/resources

    // 게시물 조회
    /*
     BoardCreateRequestDTO : order = (  like_count ) or null(post_date)

                             mapX = (123.123, 246.321) or null
                             mapY = (124, 127) or null
                                            or
                             campsiteCode = ( ,   ,   ,   ,  ) or null

                             search : ("") or null

                             pageNumber, pageSize


     BoardCreateResponseDTO : userName, userImagePath, boardContent, campsiteName, images, postDate, commentCount, likeCount, comment 2개
                               pageNumber, pageSize, total, mapX, mapY
     */
    @RequestMapping(value = "/boards", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boards(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        BoardsRequestDTO boardsRequestDTO = gson.fromJson(request, BoardsRequestDTO.class);

        // 같이 고민해봅시다.


        return gson.toJson(response);
    }



    // 게시물 생성
    /*
     BoardCreateRequestDTO : userId, campsiteCode, campsiteName, mapX, mapY, boardContent, images(List)
     BoardCreateResponseDTO : boardContent, campsiteCode, imagePath(list)
     */
    @RequestMapping(value = "/board/create", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardCreate(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        String imagePath = imageService.getBasePath();
        Map<String, Object> response = new HashMap<>();


        // json to boardCreateRequestDTO
        BoardCreateRequestDTO boardCreateRequestDTO = gson.fromJson(request, BoardCreateRequestDTO.class);

        // if user id not found
        if(userService.findUserByUserId(boardCreateRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // if campsite not existed => insert campsite in campsites table
        if (campsiteService.findCampsiteNameByCode(boardCreateRequestDTO.getCampsiteCode()) == null) {
            CampsiteDTO campsite = new CampsiteDTO(boardCreateRequestDTO.getCampsiteCode(),
                    boardCreateRequestDTO.getCampsiteName(),
                    boardCreateRequestDTO.getMapX(), boardCreateRequestDTO.getMapY());
            campsiteService.insertCampsite(campsite);
        }

        // decoding images
        ArrayList<String> responseImagePath = new ArrayList<String>();
        ArrayList<String> imageNames = new ArrayList<String>();
        for (int i = 0; i < boardCreateRequestDTO.getImages().size(); i++) {
            imageNames.add(imageService.getImageNames(boardCreateRequestDTO.getImages().get(i)));
            responseImagePath.add(imagePath + "/" + imageNames.get(i));
        }

        // insert board in boards table
        BoardDTO board = new BoardDTO(boardCreateRequestDTO.getUserId(),
                boardCreateRequestDTO.getCampsiteCode(),
                boardCreateRequestDTO.getBoardContent()
        );
        boardService.insertBoard(board);

        // make response body
        BoardCreateResponseDTO boardCreateResponseDTO = new BoardCreateResponseDTO(
                boardCreateRequestDTO.getBoardContent(),
                boardCreateRequestDTO.getCampsiteCode(),
                responseImagePath
        );

        // insert images in images table
        int curBoardId = boardService.findLastBoardId().getBoardId();
        for (int i = 0; i < imageNames.size(); i++) {
            ImageDTO image = new ImageDTO(curBoardId,
                    imageNames.get(i),
                    imageService.getBasePath());
            imageService.insertImages(image);
        }
        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", boardCreateResponseDTO);
        return gson.toJson(response);
    }

    // 게시물 수정
    /*
    BoardUpdateRequestDTO : userId, boardId, campsiteCode, campsiteName, mapX, mapY, boardContent
    BoardUpdateResponseDTO : boardContent, campsiteCode, firstImagePath
     */
    @RequestMapping(value = "/board/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardUpdate(@RequestBody String request){
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to boardUpdateRequestDTO
        BoardUpdateRequestDTO boardUpdateRequestDTO = gson.fromJson(request, BoardUpdateRequestDTO.class);
        BoardDTO board = boardService.findBoardByBoardId(boardUpdateRequestDTO.getBoardId());
        UserDTO user = userService.findUserByUserId(boardUpdateRequestDTO.getUserId());

        // userId, boardId 없으면 400 bad request
        if(board == null || user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        // userId 다르면 401 unauthorized
        if(board.getUserId() != (user.getUserId())) {
            response.put("responseCode", 401);
            response.put("responseMessage", "Unauthorized");
            return gson.toJson(response);
        }

        // if campsite not existed => insert campsite in campsites table
        if (campsiteService.findCampsiteNameByCode(boardUpdateRequestDTO.getCampsiteCode()) == null) {
            CampsiteDTO campsite = new CampsiteDTO(boardUpdateRequestDTO.getCampsiteCode(),
                    boardUpdateRequestDTO.getCampsiteName(),
                    boardUpdateRequestDTO.getMapX(), boardUpdateRequestDTO.getMapY());
            campsiteService.insertCampsite(campsite);
        }

        // update board
        board.setBoardContent(boardUpdateRequestDTO.getBoardContent());
        board.setCampsiteCode(boardUpdateRequestDTO.getCampsiteCode());
        boardService.updateBoard(board);

        List<ImageDTO> images = imageService.findImagesByBoardId(board.getBoardId());
        ArrayList<String> responseImagePath = new ArrayList<String>();

        for(int i=0; i<images.size(); i++) {
            responseImagePath.add(images.get(i).getImagePath() + "/" + images.get(i).getImageName());
        }
        // make response body
        BoardCreateResponseDTO boardCreateResponseDTO = new BoardCreateResponseDTO(
                boardUpdateRequestDTO.getBoardContent(),
                boardUpdateRequestDTO.getCampsiteCode(),
                responseImagePath
        );
        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", boardCreateResponseDTO);
        return gson.toJson(response);
    }

    // 게시물 삭제
    /*
    BoardDeleteRequestDTO : boardId, userId
    BoardDeleteResponseDTO : x
     */
    @RequestMapping(value = "/board/delete", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardDelete(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        BoardDeleteRequestDTO boardDeleteRequestDTO = gson.fromJson(request, BoardDeleteRequestDTO.class);
        BoardDTO board = boardService.findBoardByBoardId(boardDeleteRequestDTO.getBoardId());
        UserDTO user = userService.findUserByUserId(boardDeleteRequestDTO.getUserId());

        // userId 없거나 boardId 없으면 400 bad request
        if(user == null || board == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // userId 다르고 auth = 0 이면 401 unauthorized
        if(board.getUserId() != (user.getUserId()) && !user.isUserAuth()) {
            response.put("responseCode", 401);
            response.put("responseMessage", "Unauthorized");
            return gson.toJson(response);
        }
        // 사진 파일 찾아서 물리적 삭제 : findImagesByBoardId
        List<ImageDTO> images = imageService.findImagesByBoardId(boardDeleteRequestDTO.getBoardId());
        for(int i=0; i<images.size(); i++) {
            String imagePath = images.get(i).getImagePath();
            String imageName = images.get(i).getImageName();
            imageService.deleteImage(imagePath, imageName);
        }

        imageService.deleteImagesByBoardId(boardDeleteRequestDTO.getBoardId());
        commentService.deleteCommentsByBoardId(boardDeleteRequestDTO.getBoardId());
        likeService.deleteLikesByBoardId(boardDeleteRequestDTO.getBoardId());
        boardService.deleteBoard(boardDeleteRequestDTO.getBoardId());

        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        response.put("responseBody", null);
        return gson.toJson(response);
    }

    // 캠핑장 코드별 개수
    /*
    Request : X
    campsiteCountResponseDTO : List<campsiteCode, boardCnt>
     */
    @RequestMapping(value = "/campsite/count", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String campsiteCount() {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        List<CampsiteCountResponseDTO> campsiteCountResponseDTOS =
                boardService.getCampsiteCount();

        if(campsiteCountResponseDTOS.size() == 0) {
            response.put("responseCode", 204);
            response.put("responseMessage", "No Content");
            return gson.toJson(response);
        }
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", campsiteCountResponseDTOS);
        return gson.toJson(response);
    }

}
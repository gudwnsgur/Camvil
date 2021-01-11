package com.app.camvil.controller;

import com.app.camvil.dto.*;
import com.app.camvil.dto.requestdto.BoardCreateRequestDTO;
import com.app.camvil.dto.requestdto.BoardDeleteRequestDTO;
import com.app.camvil.dto.requestdto.BoardUpdateRequestDTO;
import com.app.camvil.dto.requestdto.BoardsRequestDTO;
import com.app.camvil.dto.responsedto.*;
import com.app.camvil.service.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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

    @RequestMapping(value = "/boards", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boards(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseBody = new HashMap<>();
        BoardsRequestDTO boardsRequestDTO = gson.fromJson(request, BoardsRequestDTO.class);
        boardsRequestDTO.setPageNumber();

        String order = (boardsRequestDTO.getOrder() == null ||
                boardsRequestDTO.getOrder().equals("") ||
                boardsRequestDTO.getOrder().equals("post_date")) ? "post_date" : "like_cnt";
        String campsiteCode = boardsRequestDTO.getCampsiteCode() == null ? "" : boardsRequestDTO.getCampsiteCode();
        String search = boardsRequestDTO.getSearch() == null ? "" : boardsRequestDTO.getSearch();

        if (!search.equals("")) {
            if (userService.findSearchBySearchContent(search) == null) {
                userService.insertSearchContent(search);
            }
            userService.increaseSearchContent(search);
        }

        long total = boardService.getTotalBoardCnt(search, campsiteCode, order);
        List<BoardsDTO> boardsDTOS = boardService.getBoards(search, campsiteCode, order,
                boardsRequestDTO.getPageSize(),
                boardsRequestDTO.getPageSize() * (boardsRequestDTO.getPageNumber() - 1));
        order = null;
        campsiteCode = null;
        search = null;


        List<BoardsResponseDTO> boardsResponseDTO = new ArrayList<>();

        for (BoardsDTO boardsDTO : boardsDTOS) {
            long curBoardId = boardsDTO.getBoardId();
            List<ImageListDTO> images = imageService.findImageListByBoardId(curBoardId);
            List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(curBoardId);

            BoardsResponseDTO curBoard = new BoardsResponseDTO(boardsDTO, images, comments);
            boardsResponseDTO.add(curBoard);
            curBoard = null;
            boardsDTO = null;
            images = null;
            comments = null;
        }
        boardsDTOS = null;

        responseBody.put("items", boardsResponseDTO);
        responseBody.put("pageNumber", boardsRequestDTO.getPageNumber());
        responseBody.put("pageSize", boardsRequestDTO.getPageSize());
        responseBody.put("total", total);

        boardsResponseDTO = null;
        boardsRequestDTO = null;

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);
        responseBody = null;

        return gson.toJson(response);
    }

    // 게시물 생성
    @RequestMapping(value = "/board/create", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardCreate(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to boardCreateRequestDTO
        BoardCreateRequestDTO boardCreateRequestDTO = gson.fromJson(request, BoardCreateRequestDTO.class);

        // if user id not found
        if (userService.findUserByUserId(boardCreateRequestDTO.getUserId()) == null) {
            boardCreateRequestDTO = null;
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
            campsite = null;
        }

        ArrayList<String> imageNames = new ArrayList<>();
        if (boardCreateRequestDTO.getImages() != null
                && !boardCreateRequestDTO.getImages().isEmpty()) {
            // decoding images
            for (int i = 0; i < boardCreateRequestDTO.getImages().size(); i++) {
                imageNames.add(imageService.getImageNames(boardCreateRequestDTO.getImages().get(i)));
            }
        }
        // insert board in boards table
        BoardDTO board = new BoardDTO(boardCreateRequestDTO.getUserId(),
                boardCreateRequestDTO.getCampsiteCode(),
                boardCreateRequestDTO.getBoardContent()
        );
        boardService.insertBoard(board);
        board = null;

        long curBoardId = boardService.findLastBoardId().getBoardId();
        UserDTO user = userService.findUserByUserId(boardCreateRequestDTO.getUserId());

        if (!imageNames.isEmpty()) {
            // insert images in images table
            for (String imageName : imageNames) {
                ImageDTO image = new ImageDTO(curBoardId, imageName, "/images");
                imageService.insertImages(image);
                image = null;
                imageName = null;
            }
        }
        imageNames = null;
        List<ImageListDTO> responseImageList = imageService.findImageListByBoardId(curBoardId);

        // make response body
        BoardCreateResponseDTO boardCreateResponseDTO = new BoardCreateResponseDTO(
                curBoardId,
                user.getUserId(),
                user.getUserName(),
                user.getUserImagePath(),
                boardCreateRequestDTO.getBoardContent(),
                boardCreateRequestDTO.getCampsiteCode(),
                responseImageList,
                boardService.findBoardByBoardId(curBoardId).getPostDate(),
                boardService.findBoardByBoardId(curBoardId).getUpdateDate()
        );

        boardCreateRequestDTO = null;
        user = null;
        responseImageList = null;

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", boardCreateResponseDTO);
        boardCreateResponseDTO = null;
        return gson.toJson(response);
    }

    // 게시물 수정
    @RequestMapping(value = "/board/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardUpdate(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to boardUpdateRequestDTO
        BoardUpdateRequestDTO boardUpdateRequestDTO = gson.fromJson(request, BoardUpdateRequestDTO.class);
        BoardDTO board = boardService.findBoardByBoardId(boardUpdateRequestDTO.getBoardId());
        UserDTO user = userService.findUserByUserId(boardUpdateRequestDTO.getUserId());

        // userId, boardId 없으면 400 bad request
        if (board == null || user == null) {
            boardUpdateRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        // userId 다르면 401 unauthorized
        if (board.getUserId() != (user.getUserId())) {
            board = null;
            user = null;
            boardUpdateRequestDTO = null;
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
            campsite = null;
        }

        // update board
        board.setBoardContent(boardUpdateRequestDTO.getBoardContent());
        board.setCampsiteCode(boardUpdateRequestDTO.getCampsiteCode());
        boardService.updateBoard(board);

        List<ImageListDTO> responseImageList = imageService.findImageListByBoardId(boardUpdateRequestDTO.getBoardId());

        // make response body
        BoardCreateResponseDTO boardCreateResponseDTO = new BoardCreateResponseDTO(
                boardUpdateRequestDTO.getBoardId(),
                user.getUserId(),
                user.getUserName(),
                user.getUserImagePath(),
                boardUpdateRequestDTO.getBoardContent(),
                boardUpdateRequestDTO.getCampsiteCode(),
                responseImageList,
                board.getPostDate(),
                board.getUpdateDate()
        );
        // response
        board = null;
        user = null;
        responseImageList = null;
        boardUpdateRequestDTO = null;

        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", boardCreateResponseDTO);
        boardCreateResponseDTO = null;
        return gson.toJson(response);
    }

    // 게시물 삭제
    @RequestMapping(value = "/board/delete", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardDelete(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        BoardDeleteRequestDTO boardDeleteRequestDTO = gson.fromJson(request, BoardDeleteRequestDTO.class);
        BoardDTO board = boardService.findBoardByBoardId(boardDeleteRequestDTO.getBoardId());
        UserDTO user = userService.findUserByUserId(boardDeleteRequestDTO.getUserId());

        // userId 없거나 boardId 없으면 400 bad request
        if (user == null || board == null) {
            boardDeleteRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // userId 다르고 auth = 0 이면 401 unauthorized
        if (board.getUserId() != (user.getUserId()) && !user.isUserAuth()) {
            board = null;
            user = null;
            boardDeleteRequestDTO = null;

            response.put("responseCode", 401);
            response.put("responseMessage", "Unauthorized");
            return gson.toJson(response);
        }

        // 사진 파일 찾아서 물리적 삭제 : findImagesByBoardId
        List<ImageDTO> images = imageService.findImagesByBoardId(boardDeleteRequestDTO.getBoardId());
        for (ImageDTO image : images) {
            imageService.toUnusableByImageId(image.getImageId());
            image = null;
        }

        commentService.toUnusableByBoardId(boardDeleteRequestDTO.getBoardId());
        boardService.toUnusableByBoardId(boardDeleteRequestDTO.getBoardId());


        boardDeleteRequestDTO = null;
        images = null;
        board = null;
        user = null;

        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        response.put("responseBody", null);
        return gson.toJson(response);
    }

    // 캠핑장 코드별 개수
    @RequestMapping(value = "/campsite/count", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String campsiteCount() {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        List<CampsiteCountResponseDTO> campsiteCountResponseDTOS =
                boardService.getCampsiteCount();

        ArrayList<CampsiteCountResponseDTO> campsiteCountResponseDTOS1 = new ArrayList<>();

        for (CampsiteCountResponseDTO campsiteCountResponseDTO : campsiteCountResponseDTOS) {
            if (!campsiteCountResponseDTO.getCampsiteCode().equals("")) {
                campsiteCountResponseDTOS1.add(campsiteCountResponseDTO);
            }
            campsiteCountResponseDTO = null;
        }

        campsiteCountResponseDTOS = null;

        response.put("responseCode", 200);  
        response.put("responseMessage", "OK");
        response.put("responseBody", campsiteCountResponseDTOS1);
        campsiteCountResponseDTOS1 = null;
        return gson.toJson(response);
    }

    // board detail
    @RequestMapping(value = "/board/detail", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String boardDetail(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        BoardDTO board = gson.fromJson(request, BoardDTO.class);
        board = boardService.findBoardByBoardId(board.getBoardId());

        if (board == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        long boardId = board.getBoardId();
        BoardsDTO boardsDTO = boardService.getBoard(boardId);
        List<ImageListDTO> images = imageService.findImageListByBoardId(boardId);
        List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(boardId);

        BoardsResponseDTO boardsResponseDTO = new BoardsResponseDTO(boardsDTO, images, comments);

        board = null;
        boardsDTO = null;
        images = null;
        comments = null;

        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", boardsResponseDTO);
        boardsResponseDTO = null;
        return gson.toJson(response);
    }

}
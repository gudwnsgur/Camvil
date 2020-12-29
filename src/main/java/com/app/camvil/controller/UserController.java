package com.app.camvil.controller;

import com.app.camvil.dto.*;
import com.app.camvil.dto.requestdto.*;
import com.app.camvil.dto.responsedto.*;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    // sign up
    @RequestMapping(value = "/user/signUp", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String signUp(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        SignUpRequestDTO signUpRequestDTO = gson.fromJson(request, SignUpRequestDTO.class);

        if (signUpRequestDTO.getUserSid() == null || signUpRequestDTO.getUserSid().equals("") ||
                signUpRequestDTO.getUserName() == null || signUpRequestDTO.getUserName().equals("") ||
                signUpRequestDTO.getFcmToken() == null || signUpRequestDTO.getFcmToken().equals("")) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        UserDTO user = new UserDTO(signUpRequestDTO.getUserSid(), signUpRequestDTO.getUserEmail(),
                signUpRequestDTO.getUserName(), signUpRequestDTO.getFcmToken());

        if (userService.findUserByUserSid(user.getUserSid()) != null) {
            response.put("responseCode", 409);
            response.put("responseMessage", "Conflict : already existed user");
            return gson.toJson(response);
        }

        if (signUpRequestDTO.getImage() == null || signUpRequestDTO.getImage().equals("")) {
            user.setUserImagePath(null);
        } else {
            user.setUserImagePath(signUpRequestDTO.getImage());
        }
        userService.insertUser(user);

        user.setUserId(userService.findUserByUserSid(user.getUserSid()).getUserId());
        user.setJoinDate(userService.findUserByUserSid(user.getUserSid()).getJoinDate());
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO(user);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", signUpResponseDTO);
        return gson.toJson(response);
    }

    // login
    @RequestMapping(value = "/user/login", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String login(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        LoginRequestDTO loginRequestDTO = gson.fromJson(request, LoginRequestDTO.class);
        if (loginRequestDTO.getUserSid() == null || loginRequestDTO.getUserSid().equals("") ||
                loginRequestDTO.getFcmToken() == null || loginRequestDTO.getFcmToken().equals("")) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        UserDTO user = userService.findUserByUserSid(loginRequestDTO.getUserSid());
        if (user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        user.setFcmToken(loginRequestDTO.getFcmToken());
        userService.updateUserToken(user);

        String userAuth = user.isUserAuth() ? "admin" : "user";
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user, userAuth);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", loginResponseDTO);
        return gson.toJson(response);
    }

    // My Page
    @RequestMapping(value = "/user", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String user(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to MyPageRequestDTO
        MyPageRequestDTO myPageRequestDTO = gson.fromJson(request, MyPageRequestDTO.class);
        UserDTO user = userService.findUserByUserId(myPageRequestDTO.getUserId());

        if (user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(user);
        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", myPageResponseDTO);
        return gson.toJson(response);
    }

    // Update user
    @RequestMapping(value = "/user/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userUpdate(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        String imagePath =  "/images/users";

        // json to MyPageRequestDTO
        UserUpdateRequestDTO userUpdateRequestDTO = gson.fromJson(request, UserUpdateRequestDTO.class);
        UserDTO user = userService.findUserByUserId(userUpdateRequestDTO.getUserId());

        if (userService.findUserByUserId(userUpdateRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        if (userUpdateRequestDTO.getUserName() != null && !userUpdateRequestDTO.getUserName().equals("")) {
            user.setUserName(userUpdateRequestDTO.getUserName());
        }

        boolean isExternalImage = false;
        String updateImagePath = userUpdateRequestDTO.getUserImage();
        if (!user.getUserImagePath().equals(updateImagePath)) {
            if (userUpdateRequestDTO.getUserImage() == null || userUpdateRequestDTO.getUserImage().equals("")) {
                updateImagePath = null;
                isExternalImage = true;
            }
            else {
                updateImagePath = imagePath + "/" + imageService.getUserImageName(userUpdateRequestDTO.getUserImage());
            }
        }

        user.setUserImagePath(updateImagePath);
        user.setExternalImage(isExternalImage);

        userService.updateUser(user);

    MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(user);
        response.put("responseCode",200);
        response.put("responseMessage","OK");
        response.put("responseBody",myPageResponseDTO);
        return gson.toJson(response);
}

    // Delete user
    @RequestMapping(value = "/user/delete", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userDelete(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to MyPageRequestDTO
        UserDeleteRequestDTO userDeleteRequestDTO = gson.fromJson(request, UserDeleteRequestDTO.class);
        UserDTO user = userService.findUserByUserId(userDeleteRequestDTO.getUserId());

        // if not found
        if (user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        long userId = user.getUserId(); // user
        List<BoardDTO> boards = boardService.findBoardsByUserId(user.getUserId()); // boards

        // delete boards
        for (BoardDTO board : boards) {
            long boardId = board.getBoardId();

            // 사진 파일 찾아서 물리적 삭제 : findImagesByBoardId
            List<ImageDTO> images = imageService.findImagesByBoardId(boardId);
            for (ImageDTO image : images) {
                imageService.toUnusableByImageId(image.getImageId());
            }
            commentService.toUnusableByBoardId(boardId);
            boardService.toUnusableByBoardId(boardId);
        }

        // decrease likes
        List<LikeDTO> likes = likeService.findLikeBoardsByUserId(user.getUserId());
        for (LikeDTO like : likes) {
            if(boardService.findBoardByBoardId(like.getBoardId()) != null)
                boardService.decreaseLike(like.getBoardId());
        }
        // decrease comments
        List<CommentCountDTO> comments = commentService.countCommentsByUserId(user.getUserId());
        for (CommentCountDTO comment : comments) {
            if(boardService.findBoardByBoardId(comment.getBoardId()) != null)
                boardService.decreaseCommentsByBoardId(comment.getCommentCnt(), comment.getBoardId());
        }

        // delete user
        userService.toUnusableByUserId(userId);

        // response
        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        return gson.toJson(response);
    }

    // update fcmToken
    @RequestMapping(value = "/user/fcmToken", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String fcmToken(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        FcmTokenDTO fcmTokenDTO = gson.fromJson(request, FcmTokenDTO.class);
        if (userService.findUserByUserId(fcmTokenDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        userService.updateTokenByUserId(fcmTokenDTO.getUserId(), fcmTokenDTO.getFcmToken());
        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", fcmTokenDTO);
        return gson.toJson(response);
    }

    // user boards page
    @RequestMapping(value = "/user/boards", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userBoards(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseBody = new HashMap<>();

        UserIdRequestDTO userIdRequestDTO = gson.fromJson(request, UserIdRequestDTO.class);

        // if user id not found
        if(userService.findUserByUserId(userIdRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        UserDTO user = userService.findUserByUserId(userIdRequestDTO.getUserId());
        long total = boardService.getTotalBoardCntWithUserId(user.getUserId());

        List<BoardsDTO> boardsDTOS = boardService.getBoardsByUserId(user.getUserId());
        List<BoardsResponseDTO> boardsResponseDTO = new ArrayList<>();

        for (BoardsDTO boardsDTO : boardsDTOS) {
            long curBoardId = boardsDTO.getBoardId();
            List<ImageListDTO> images = imageService.findImageListByBoardId(curBoardId);
            List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(curBoardId);

            BoardsResponseDTO curBoard = new BoardsResponseDTO(boardsDTO, images, comments);
            boardsResponseDTO.add(curBoard);
        }

        responseBody.put("items", boardsResponseDTO);
        responseBody.put("total", total);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);
        return gson.toJson(response);
    }

    // user like board page
    @RequestMapping(value = "/user/likeBoards", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userLikeBoards(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseBody = new HashMap<>();

        UserIdRequestDTO userIdRequestDTO = gson.fromJson(request, UserIdRequestDTO.class);

        // if user id not found
        if(userService.findUserByUserId(userIdRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        UserDTO user = userService.findUserByUserId(userIdRequestDTO.getUserId());
        long total = boardService.getTotalLikeBoardCntWithUserId(user.getUserId());

        List<BoardsDTO> boardsDTOS = boardService.getLikeBoardsByUserId(user.getUserId());
        List<BoardsResponseDTO> boardsResponseDTO = new ArrayList<>();

        for (BoardsDTO boardsDTO : boardsDTOS) {
            long curBoardId = boardsDTO.getBoardId();
            List<ImageListDTO> images = imageService.findImageListByBoardId(curBoardId);
            List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(curBoardId);

            BoardsResponseDTO curBoard = new BoardsResponseDTO(boardsDTO, images, comments);
            boardsResponseDTO.add(curBoard);
        }

        responseBody.put("items", boardsResponseDTO);
        responseBody.put("total", total);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);
        return gson.toJson(response);
    }


    // user boards page with paging
    @RequestMapping(value = "/user/boards/paging", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userBoardsPaging(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseBody = new HashMap<>();
        UserPagingDTO userPagingDTO = gson.fromJson(request, UserPagingDTO.class);

        // if user id not found
        if(userService.findUserByUserId(userPagingDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        UserDTO user = userService.findUserByUserId(userPagingDTO.getUserId());
        userPagingDTO.setPageNumber();
        long total = boardService.getTotalBoardCntWithUserId(user.getUserId());

        List<BoardsDTO> boardsDTOS = boardService.getBoardsByUserIdWithPaging(user.getUserId(),
                userPagingDTO.getPageSize(),
                userPagingDTO.getPageSize()*(userPagingDTO.getPageNumber()-1));
        List<BoardsResponseDTO> boardsResponseDTO = new ArrayList<BoardsResponseDTO>();

        for (BoardsDTO boardsDTO : boardsDTOS) {
            long curBoardId = boardsDTO.getBoardId();
            List<ImageListDTO> images = imageService.findImageListByBoardId(curBoardId);
            List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(curBoardId);

            BoardsResponseDTO curBoard = new BoardsResponseDTO(boardsDTO, images, comments);
            boardsResponseDTO.add(curBoard);
        }

        responseBody.put("items", boardsResponseDTO);
        responseBody.put("pageNumber", userPagingDTO.getPageNumber());
        responseBody.put("pageSize", userPagingDTO.getPageSize());
        responseBody.put("total", total);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);
        return gson.toJson(response);
    }

    // user like board page with paging
    @RequestMapping(value = "/user/likeBoards/paging", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userLikeBoardsPaging(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseBody = new HashMap<>();
        UserPagingDTO userPagingDTO = gson.fromJson(request, UserPagingDTO.class);

        // if user id not found
        if(userService.findUserByUserId(userPagingDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        UserDTO user = userService.findUserByUserId(userPagingDTO.getUserId());
        userPagingDTO.setPageNumber();
        long total = boardService.getTotalLikeBoardCntWithUserId(user.getUserId());

        List<BoardsDTO> boardsDTOS = boardService.getLikeBoardsByUserIdWithPaging(user.getUserId(),
                userPagingDTO.getPageSize(),
                userPagingDTO.getPageSize()*(userPagingDTO.getPageNumber()-1));
        List<BoardsResponseDTO> boardsResponseDTO = new ArrayList<>();

        for (BoardsDTO boardsDTO : boardsDTOS) {
            long curBoardId = boardsDTO.getBoardId();
            List<ImageListDTO> images = imageService.findImageListByBoardId(curBoardId);
            List<CommentDetailResponseDTO> comments = commentService.getTwoCommentsByBoardId(curBoardId);

            BoardsResponseDTO curBoard = new BoardsResponseDTO(boardsDTO, images, comments);
            boardsResponseDTO.add(curBoard);
        }

        responseBody.put("items", boardsResponseDTO);
        responseBody.put("pageNumber", userPagingDTO.getPageNumber());
        responseBody.put("pageSize", userPagingDTO.getPageSize());
        responseBody.put("total", total);

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);
        return gson.toJson(response);
    }
}
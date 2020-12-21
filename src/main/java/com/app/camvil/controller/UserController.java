package com.app.camvil.controller;

import com.app.camvil.dto.*;
import com.app.camvil.dto.requestdto.*;
import com.app.camvil.dto.responsedto.LoginResponseDTO;
import com.app.camvil.dto.responsedto.MyPageResponseDTO;
import com.app.camvil.dto.responsedto.SignUpResponseDTO;
import com.app.camvil.service.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        String imagePath = imageService.getBasePath() + "/users";

        // json to MyPageRequestDTO
        UserUpdateRequestDTO userUpdateRequestDTO = gson.fromJson(request, UserUpdateRequestDTO.class);
        UserDTO user = userService.findUserByUserId(userUpdateRequestDTO.getUserId());

        if (userService.findUserByUserId(userUpdateRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        if (userUpdateRequestDTO.getUserName() == null || userUpdateRequestDTO.getUserName().equals("")) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // 안 바뀌면 path
        // url -> null
        // url -> real
        // null -> real
        // real -> real(수정)
        // real -> null
        boolean isExternalImage = false;
        String updateImagePath = userUpdateRequestDTO.getUserImage();
        if (!user.getUserImagePath().equals(updateImagePath)) {
            // url or null =>
            if(user.isExternalImage()) {
                // => null
                if(userUpdateRequestDTO.getUserImage() == null || userUpdateRequestDTO.getUserImage() .equals("")) {
                    updateImagePath = null;
                    isExternalImage = true;
                }
                // => real
                else {
                    updateImagePath =  imagePath + "/" + imageService.getUserImageName(userUpdateRequestDTO.getUserImage());
                }
            }
            // real =>
            else {
                imageService.deleteImage(user.getUserImagePath());
                if(userUpdateRequestDTO.getUserImage() == null || userUpdateRequestDTO.getUserImage() .equals("")) {
                    updateImagePath = null;
                    isExternalImage = true;
                }
                else {
                    updateImagePath =  imagePath + "/" + imageService.getUserImageName(userUpdateRequestDTO.getUserImage());
                }
            }
        }


        user.setUserName(userUpdateRequestDTO.getUserName());
        user.setUserImagePath(updateImagePath);
        user.setExternalImage(isExternalImage);

        userService.updateUser(user);

        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(user);
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", myPageResponseDTO);
        return gson.toJson(response);
    }

    // Delete user
     /*
    UserDeleteRequestDTO : userId
    response : X
    */
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

        // delete user profile image
        if (!user.getUserImagePath().equals(imageService.getBaseProfileImage())) {
            imageService.deleteImage(user.getUserImagePath());
        }
        long userId = user.getUserId(); // user
        List<BoardDTO> boards = boardService.findBoardsByUserId(user.getUserId()); // boards

        // delete boards
        for (int i = 0; i < boards.size(); i++) {
            long boardId = boards.get(i).getBoardId();

            // 사진 파일 찾아서 물리적 삭제 : findImagesByBoardId
            List<ImageDTO> images = imageService.findImagesByBoardId(boardId);
            for (int j = 0; j < images.size(); j++) {
                String imagePaths = images.get(j).getImagePath();
                String imageName = images.get(j).getImageName();
                imageService.deleteImage(imagePaths, imageName);
            }
            imageService.deleteImagesByBoardId(boardId);
            commentService.deleteCommentsByBoardId(boardId);
            likeService.deleteLikesByBoardId(boardId);
            boardService.deleteBoard(boardId);
        }

        // decrease likes
        List<LikeDTO> likes = likeService.findLikeBoardsByUserId(user.getUserId());
        for (int i = 0; i < likes.size(); i++) {
            boardService.decreaseLike(likes.get(i).getBoardId());
        }
        // decrease comments
        List<CommentCountDTO> comments = commentService.countCommentsByUserId(user.getUserId());
        for (int i = 0; i < comments.size(); i++) {
            boardService.decreaseCommentsByBoardId(comments.get(i).getCommentCnt(), comments.get(i).getBoardId());
        }

        // delete user
        userService.deleteUser(userId);

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

}
package com.app.camvil.controller;

import com.app.camvil.dto.*;
import com.app.camvil.dto.requestdto.MyPageRequestDTO;
import com.app.camvil.dto.requestdto.UserDeleteRequestDTO;
import com.app.camvil.dto.requestdto.UserUpdateRequestDTO;
import com.app.camvil.dto.responsedto.MyPageResponseDTO;
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

    // My Page
    /*
    MyPageRequestDTO : userId
    MyPageResponseDTO : userEmail, userName, userImagePath, joinDate
    */
    @RequestMapping(value = "/user", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String user(@RequestBody String request){
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to MyPageRequestDTO
        MyPageRequestDTO myPageRequestDTO = gson.fromJson(request, MyPageRequestDTO.class);
        UserDTO user = userService.findUserByUserId(myPageRequestDTO.getUserId());

        if(user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(
                user.getUserEmail(),
                user.getUserName(),
                user.getUserImagePath(),
                user.getJoinDate()
        );

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", myPageResponseDTO);
        return gson.toJson(response);
    }

    // Update user
     /*
    UserUpdateRequestDTO : userId, userName, userImage(encoded),
    MyPageResponseDTO : userEmail, userName(updated), userImagePath(updated), joinDate
    */
    @RequestMapping(value = "/user/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String userUpdate(@RequestBody String request) throws IOException {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        String imagePath = imageService.getBasePath() + "/users";

        // json to MyPageRequestDTO
        UserUpdateRequestDTO userUpdateRequestDTO = gson.fromJson(request, UserUpdateRequestDTO.class);
        UserDTO user = userService.findUserByUserId(userUpdateRequestDTO.getUserId());

        if(userService.findUserByUserId(userUpdateRequestDTO.getUserId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }


        // delete original user profile image
        imageService.deleteImage(user.getUserImagePath());

        // BASE64 decoding & save image file
        String newImageName = imageService.getUserImageName(userUpdateRequestDTO.getUserImage());

        // update user
        user.setUserImagePath(imagePath + "/"  + newImageName);
        user.setUserName(userUpdateRequestDTO.getUserName());
        userService.updateUser(user);

        // make response
        MyPageResponseDTO myPageResponseDTO = new MyPageResponseDTO(
                user.getUserEmail(), user.getUserName(), user.getUserImagePath(), user.getJoinDate());
        // response
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
        String imagePath = imageService.getBasePath() + "/users";

        // json to MyPageRequestDTO
        UserDeleteRequestDTO userDeleteRequestDTO = gson.fromJson(request, UserDeleteRequestDTO.class);
        UserDTO user = userService.findUserByUserId(userDeleteRequestDTO.getUserId());

        // if not found
        if(user == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // delete user profile image
        imageService.deleteImage(user.getUserImagePath());
        int userId = user.getUserId(); // user
        List<BoardDTO> boards = boardService.findBoardsByUserId(user.getUserId()); // boards

        // delete boards
        for(int i=0; i<boards.size(); i++) {
            int boardId = boards.get(i).getBoardId();

            // 사진 파일 찾아서 물리적 삭제 : findImagesByBoardId
            List<ImageDTO> images = imageService.findImagesByBoardId(boardId);
            for(int j=0; j<images.size(); j++) {
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
        for(int i=0; i<likes.size(); i++) {
            boardService.decreaseLike(likes.get(i).getBoardId());
        }
        // decrease comments
        List<CommentCountDTO> comments = commentService.countCommentsByUserId(user.getUserId());
        for(int i=0; i<comments.size(); i++ ) {
            boardService.decreaseCommentsByBoardId(comments.get(i).getCommentCnt(), comments.get(i).getBoardId());
        }

        // delete user
        userService.deleteUser(userId);

        // response
        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        return gson.toJson(response);
    }
}
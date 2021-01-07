package com.app.camvil.controller;

import com.app.camvil.dto.LikeDTO;
import com.app.camvil.dto.requestdto.LikeRequestDTO;
import com.app.camvil.service.BoardService;
import com.app.camvil.service.LikeService;
import com.app.camvil.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LikeController {
    @Autowired
    LikeService likeService;
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;

    // 좋아요 or 좋아요 취소
    @RequestMapping(value = "/like", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String like(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to boardCreateRequestDTO
        LikeRequestDTO likeRequestDTO = gson.fromJson(request, LikeRequestDTO.class);

        // if user_id not found || if board_id not found
        if(userService.findUserByUserId(likeRequestDTO.getUserId()) == null ||
                boardService.findBoardByBoardId(likeRequestDTO.getBoardId()) == null) {
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // insert like
        LikeDTO like = new LikeDTO(likeRequestDTO.getUserId(), likeRequestDTO.getBoardId());
        if(likeService.findLike(likeRequestDTO.getUserId(), likeRequestDTO.getBoardId()) == null) {
            likeService.insertLike(like);
        }
        boolean likeStatus = likeRequestDTO.getLike_().equals("true");

        if(likeService.findLike(likeRequestDTO.getUserId(), likeRequestDTO.getBoardId()).isLike_() && !likeStatus) {
            boardService.decreaseLike(like.getBoardId());
            likeService.updateLikeToUnlike(like);
        }
        else if(!likeService.findLike(likeRequestDTO.getUserId(), likeRequestDTO.getBoardId()).isLike_() && likeStatus) {
            boardService.increaseLike(like.getBoardId());
            likeService.updateUnlikeToLike(like);
        }
        likeRequestDTO = null;
        like = null;

        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        return gson.toJson(response);
    }
}
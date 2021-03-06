package com.app.camvil.controller;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.CommentDTO;
import com.app.camvil.dto.UserDTO;
import com.app.camvil.dto.requestdto.CommentCreateRequestDTO;
import com.app.camvil.dto.requestdto.CommentDeleteRequestDTO;
import com.app.camvil.dto.requestdto.CommentUpdateRequestDTO;
import com.app.camvil.dto.responsedto.CommentDetailResponseDTO;
import com.app.camvil.dto.responsedto.CommentResponseDTO;
import com.app.camvil.service.BoardService;
import com.app.camvil.service.CommentService;
import com.app.camvil.service.UserService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @RequestMapping(value = "/comment/create", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String commentCreate(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to CommentCreateRequestDTO
        CommentCreateRequestDTO commentCreateRequestDTO = gson.fromJson(request, CommentCreateRequestDTO.class);


        // if user_id not found || if board_id not found
        if (userService.findUserByUserId(commentCreateRequestDTO.getUserId()) == null ||
                boardService.findBoardByBoardId(commentCreateRequestDTO.getBoardId()) == null) {
            commentCreateRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        // insert comment in comments table
        CommentDTO commentDTO = new CommentDTO(commentCreateRequestDTO.getCommentContent(),
                commentCreateRequestDTO.getUserId(),
                commentCreateRequestDTO.getBoardId());
        commentService.insertComment(commentDTO);

        // comment count + 1 in board
        boardService.increaseComment(commentCreateRequestDTO.getBoardId());
        CommentDTO comment = commentService.findLastCommentId();


        commentDTO = null;
        commentCreateRequestDTO = null;
        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", comment);
        comment = null;
        return gson.toJson(response);
    }

    // 댓글 수정
    /*
    CommentCreateRequestDTO : commentId, userId, commentContent
    CommentCreateResponseDTO : NULL
    */
    @RequestMapping(value = "/comment/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String commentUpdate(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to CommentCreateRequestDTO
        CommentUpdateRequestDTO commentUpdateRequestDTO = gson.fromJson(request, CommentUpdateRequestDTO.class);
        UserDTO user = userService.findUserByUserId(commentUpdateRequestDTO.getUserId());

        // if user_id not found || if comment_id not found
        if (userService.findUserByUserId(commentUpdateRequestDTO.getUserId()) == null ||
                commentService.findCommentByCommentId(commentUpdateRequestDTO.getCommentId()) == null) {
            user = null;
            commentUpdateRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        // if board_id not found
        if (boardService.findBoardByBoardId(commentService.findCommentByCommentId(
                commentUpdateRequestDTO.getCommentId()).getBoardId()) == null) {
            user = null;
            commentUpdateRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        if (commentService.findCommentByCommentId(commentUpdateRequestDTO.getCommentId()).getUserId() !=
                (user.getUserId())) {
            user = null;
            commentUpdateRequestDTO = null;

            response.put("responseCode", 401);
            response.put("responseMessage", "Unauthorized");
            return gson.toJson(response);
        }

        // update comment in comments table
        CommentDTO commentDTO = new CommentDTO(
                commentUpdateRequestDTO.getCommentId(),
                commentUpdateRequestDTO.getCommentContent());
        commentService.updateComment(commentDTO);


        commentDTO = commentService.findCommentByCommentId(commentUpdateRequestDTO.getCommentId());

        commentUpdateRequestDTO = null;
        user = null;

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", commentDTO);
        commentDTO = null;
        return gson.toJson(response);
    }


    // 댓글 삭제
    @RequestMapping(value = "/comment/delete", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String commentDelete(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to CommentCreateRequestDTO
        CommentDeleteRequestDTO commentDeleteRequestDTO = gson.fromJson(request, CommentDeleteRequestDTO.class);

        // if user_id not found || if comment_id not found
        if (userService.findUserByUserId(commentDeleteRequestDTO.getUserId()) == null ||
                commentService.findCommentByCommentId(commentDeleteRequestDTO.getCommentId()) == null) {
            commentDeleteRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }
        // if board_id not found
        if (boardService.findBoardByBoardId(commentService.findCommentByCommentId(
                commentDeleteRequestDTO.getCommentId()).getBoardId()) == null) {
            commentDeleteRequestDTO = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        UserDTO user = userService.findUserByUserId(commentDeleteRequestDTO.getUserId());

        if (commentService.findCommentByCommentId(commentDeleteRequestDTO.getCommentId()).getUserId() != (user.getUserId())
                && boardService.findBoardByBoardId(
                commentService.findCommentByCommentId(commentDeleteRequestDTO.getCommentId()).getBoardId())
                .getUserId() != (user.getUserId())
                && !user.isUserAuth()
        ) {
            user = null;
            commentDeleteRequestDTO = null;
            response.put("responseCode", 401);
            response.put("responseMessage", "Unauthorized");
            return gson.toJson(response);
        }

        // comment count - 1 in board
        boardService.decreaseComment(commentService.findCommentByCommentId(commentDeleteRequestDTO.getCommentId()).getBoardId());
        // delete comment in comments table
        commentService.toUnusableByCommentId(commentDeleteRequestDTO.getCommentId());


        commentDeleteRequestDTO = null;
        user = null;

        // response
        response.put("responseCode", 204);
        response.put("responseMessage", "No Content");
        response.put("responseBody", null);
        return gson.toJson(response);
    }


    // 상세한 댓글 보기
    @RequestMapping(value = "/comment/detail", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public String commentDetail(@RequestBody String request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();

        // json to DTO
        BoardDTO board = gson.fromJson(request, BoardDTO.class);
        if (boardService.findBoardByBoardId(board.getBoardId()) == null) {
            board = null;
            response.put("responseCode", 400);
            response.put("responseMessage", "Bad Request");
            return gson.toJson(response);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("boardId", board.getBoardId());
        responseBody.put("total", boardService.getCommentCountByBoardId(board.getBoardId()));

        List<CommentDetailResponseDTO> commentDetailResponseDTOS =
                commentService.getCommentsByBoardId(board.getBoardId());
        responseBody.put("items", commentDetailResponseDTOS);


        commentDetailResponseDTOS = null;
        board = null;

        // response
        response.put("responseCode", 200);
        response.put("responseMessage", "OK");
        response.put("responseBody", responseBody);

        responseBody = null;
        return gson.toJson(response);
    }
}
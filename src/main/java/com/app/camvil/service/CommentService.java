package com.app.camvil.service;

import com.app.camvil.dto.CommentCountDTO;
import com.app.camvil.dto.CommentDTO;
import com.app.camvil.dto.responsedto.CommentDetailResponseDTO;
import com.app.camvil.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public CommentDTO findCommentByCommentId(int commentId) {
        return repository.findCommentByCommentId(commentId);
    }
    public List<CommentDTO> findCommentsByBoardId(int boardId) {
        return repository.findCommentsByBoardId(boardId);
    }
    public List<CommentDTO> findCommentsByUserId(int userId) {
        return repository.findCommentsByUserId(userId);
    }
    public List<CommentDetailResponseDTO> getCommentsByBoardId(int boardId) {return repository.getCommentsByBoardId(boardId);}

    public List<CommentCountDTO> countCommentsByUserId(int userId) {
        return repository.countCommentsByUserId(userId);
    }

    public void insertComment(CommentDTO commentDTO) {
        repository.insertComment(commentDTO);
    }
    public void updateComment(CommentDTO commentDTO){
        repository.updateComment(commentDTO);
    }

    public void deleteCommentByCommentId(int commentId) {
        repository.deleteCommentByCommentId(commentId);
    }
    public void deleteCommentsByBoardId(int boardId) {
        repository.deleteCommentsByBoardId(boardId);
    }
    public void deleteCommentsByUserId(int userId) {
        repository.deleteCommentsByUserId(userId);
    }
}
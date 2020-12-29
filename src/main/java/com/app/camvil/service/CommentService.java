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

    public CommentDTO findCommentByCommentId(long commentId) {
        return repository.findCommentByCommentId(commentId);
    }
    public CommentDTO findLastCommentId() {return repository.findLastCommentId();}
    public List<CommentDTO> findCommentsByBoardId(long boardId) {
        return repository.findCommentsByBoardId(boardId);
    }
    public List<CommentDTO> findCommentsByUserId(long userId) {
        return repository.findCommentsByUserId(userId);
    }
    public List<CommentDetailResponseDTO> getCommentsByBoardId(long boardId) {return repository.getCommentsByBoardId(boardId);}
    public List<CommentDetailResponseDTO> getTwoCommentsByBoardId(long boardId) {return repository.getTwoCommentsByBoardId(boardId);}

    public List<CommentCountDTO> countCommentsByUserId(long userId) {
        return repository.countCommentsByUserId(userId);
    }

    public void insertComment(CommentDTO commentDTO) {
        repository.insertComment(commentDTO);
    }
    public void updateComment(CommentDTO commentDTO){
        repository.updateComment(commentDTO);
    }

    public void deleteCommentByCommentId(long commentId) {
        repository.deleteCommentByCommentId(commentId);
    }
    public void deleteCommentsByBoardId(long boardId) {
        repository.deleteCommentsByBoardId(boardId);
    }
    public void deleteCommentsByUserId(long userId) {
        repository.deleteCommentsByUserId(userId);
    }

    public boolean isUsable(long commentId) {return repository.isUsable(commentId);}
    public void toUnusableByCommentId(long commentId) {repository.toUnusableByCommentId(commentId);}
    public void toUnusableByBoardId(long boardId) {repository.toUnusableByBoardId(boardId);}
    public void toUnusableByUserId(long userId) {repository.toUnusableByUserId(userId);}
}
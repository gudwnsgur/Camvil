package com.app.camvil.repository;

import com.app.camvil.dto.CommentCountDTO;
import com.app.camvil.dto.CommentDTO;
import com.app.camvil.dto.responsedto.CommentDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentRepository {
    CommentDTO findCommentByCommentId(int commentId);
    List<CommentDTO> findCommentsByBoardId(int boardId);
    List<CommentDTO> findCommentsByUserId(int userId);

    List<CommentDetailResponseDTO> getCommentsByBoardId(int boardId);

    List<CommentCountDTO> countCommentsByUserId(int userId);

    void insertComment(CommentDTO commentDTO);
    void updateComment(CommentDTO commentDTO);

    void deleteCommentByCommentId(int commentId);
    void deleteCommentsByBoardId(int boardId);
    void deleteCommentsByUserId(int userId);

}
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
    CommentDTO findCommentByCommentId(long commentId);
    CommentDTO findLastCommentId();
    List<CommentDTO> findCommentsByBoardId(long boardId);
    List<CommentDTO> findCommentsByUserId(long userId);

    List<CommentDetailResponseDTO> getCommentsByBoardId(long boardId);
    List<CommentDetailResponseDTO> getTwoCommentsByBoardId(long boardId);

    List<CommentCountDTO> countCommentsByUserId(long userId);

    void insertComment(CommentDTO commentDTO);
    void updateComment(CommentDTO commentDTO);

    void deleteCommentByCommentId(long commentId);
    void deleteCommentsByBoardId(long boardId);
    void deleteCommentsByUserId(long userId);

    boolean isUsable(long boardId);
    void toUnusableByCommentId(long commentId);
    void toUnusableByBoardId(long boardId);
    void toUnusableByUserId(long userId);

}
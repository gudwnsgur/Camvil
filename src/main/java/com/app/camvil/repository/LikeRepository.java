package com.app.camvil.repository;

import com.app.camvil.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LikeRepository {
    LikeDTO findLike(int userId, int boardId);
    List<LikeDTO> findLikedBoardsByUserId(int userId);

    void insertLike(LikeDTO likeDTO);
    void updateLikeToUnlike(LikeDTO likeDTO);
    void updateUnlikeToLike(LikeDTO likeDTO);

    void deleteLikesByBoardId(int boardId);
    void deleteLikesByUserId(int userId);
}

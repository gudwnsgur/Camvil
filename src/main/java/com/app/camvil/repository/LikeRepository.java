package com.app.camvil.repository;

import com.app.camvil.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LikeRepository {
    LikeDTO findLike(long userId, long boardId);
    List<LikeDTO> findLikedBoardsByUserId(long userId);

    void insertLike(LikeDTO likeDTO);
    void updateLikeToUnlike(LikeDTO likeDTO);
    void updateUnlikeToLike(LikeDTO likeDTO);

    void deleteLikesByBoardId(long boardId);
    void deleteLikesByUserId(long userId);
}

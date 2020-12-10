package com.app.camvil.service;

import com.app.camvil.dto.LikeDTO;
import com.app.camvil.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository repository;

    public LikeDTO findLike(long userId, long boardId) {
        return repository.findLike(userId, boardId);
    }
    public List<LikeDTO> findLikeBoardsByUserId(long userId) {
        return repository.findLikedBoardsByUserId(userId);
    }
    public void insertLike(LikeDTO likeDTO) {
        repository.insertLike(likeDTO);
    }
    public void updateLikeToUnlike(LikeDTO likeDTO) {
        repository.updateLikeToUnlike(likeDTO);
    }
    public void updateUnlikeToLike(LikeDTO likeDTO) {
        repository.updateUnlikeToLike(likeDTO);
    }
    public void deleteLikesByBoardId(long boardId) {
        repository.deleteLikesByBoardId(boardId);
    }
    public void deleteLikesByUserId(long userId) {
        repository.deleteLikesByUserId(userId);}
}

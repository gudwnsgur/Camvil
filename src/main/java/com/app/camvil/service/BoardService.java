package com.app.camvil.service;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.responsedto.CampsiteCountResponseDTO;
import com.app.camvil.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    public BoardDTO findLastBoardId() {return repository.findLastBoardId();}
    public BoardDTO findBoardByBoardId(int boardId) {
        return repository.findBoardByBoardId(boardId);
    }
    public List<BoardDTO> findBoardsByUserId(int userId) {
        return repository.findBoardsByUserId(userId);
    }

    public List<BoardDTO> getBoards() {
        return repository.getBoards();
    }
    public  List<BoardDTO> getBoardsWithLikeCount() {
        return repository.getBoardsWithLikeCount();
    }
    public List<BoardDTO> getBoardsByCampsiteCode(String campsiteCode) {
        return repository.getBoardsByCampsiteCode(campsiteCode);
    }
    public List<CampsiteCountResponseDTO> getCampsiteCount() {
        return repository.getCampsiteCount();
    }
    public int getCommentCountByBoardId(int boardId) {
        return repository.getCommentCountByBoardId(boardId);
    }

    public void insertBoard(BoardDTO boardDTO) {
        repository.insertBoard(boardDTO);
    }
    public void updateBoard(BoardDTO boardDTO) {
        repository.updateBoard(boardDTO);
    }
    public void increaseLike(int boardId) {
        repository.increaseLike(boardId);
    }
    public void decreaseLike(int boardId) {
        repository.decreaseLike(boardId);
    }
    public void increaseComment(int boardId) {
        repository.increaseComment(boardId);
    }
    public void decreaseComment(int boardId) {
        repository.decreaseComment(boardId);
    }
    public void decreaseCommentsByBoardId(int commentCnt, int boardId) {
        repository.decreaseCommentsByBoardId(commentCnt, boardId);
    }

    public void deleteBoard(int boardId) {
        repository.deleteBoard(boardId);
    }


}
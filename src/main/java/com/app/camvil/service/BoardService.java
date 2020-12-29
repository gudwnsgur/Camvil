package com.app.camvil.service;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.BoardsDTO;
import com.app.camvil.dto.responsedto.CampsiteCountResponseDTO;
import com.app.camvil.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    public List<BoardsDTO> getBoards(String search, String campsiteCode, String order,
                              int limit, int offset) {
        if(campsiteCode == null || campsiteCode.equals(""))
            return repository.getBoards(search, campsiteCode, order, limit, offset);
        else
            return repository.getBoardsContainsCode(search, campsiteCode, order, limit, offset);
    }

    public List<BoardsDTO> getBoardsByUserId(long userId) {
        return repository.getBoardsByUserId(userId);
    }
    public List<BoardsDTO> getBoardsByUserIdWithPaging(long userId, int limit, int offset) {
        return repository.getBoardsByUserIdWithPaging(userId, limit, offset);
    }
    public List<BoardsDTO> getLikeBoardsByUserId(long userId) {
        return repository.getLikeBoardsByUserId(userId);
    }
    public List<BoardsDTO> getLikeBoardsByUserIdWithPaging(long userId, int limit, int offset) {
        return repository.getLikeBoardsByUserIdWithPaging(userId, limit, offset);
    }

    public BoardDTO findLastBoardId() {return repository.findLastBoardId();}
    public BoardDTO findBoardByBoardId(long boardId) {
        return repository.findBoardByBoardId(boardId);
    }
    public List<BoardDTO> findBoardsByUserId(long userId) {
        return repository.findBoardsByUserId(userId);
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
    public long getCommentCountByBoardId(long boardId) {
        return repository.getCommentCountByBoardId(boardId);
    }
    public long getTotalBoardCnt(String search, String campsiteCode, String order) {
        if(campsiteCode == null || campsiteCode.equals(""))
            return repository.getTotalBoardCnt(search, campsiteCode, order);
        else
            return repository.getTotalBoardCntContainsCode(search, campsiteCode, order);
    }
    public long getTotalBoardCntWithUserId(long userId) {
        return repository.getTotalBoardCntWithUserId(userId);
    }
    public long getTotalLikeBoardCntWithUserId(long userId) {
        return repository.getTotalLikeBoardCntWithUserId(userId);
    }


    public void insertBoard(BoardDTO boardDTO) {
        repository.insertBoard(boardDTO);
    }
    public void updateBoard(BoardDTO boardDTO) {
        repository.updateBoard(boardDTO);
    }
    public void increaseLike(long boardId) {
        repository.increaseLike(boardId);
    }
    public void decreaseLike(long boardId) {
        repository.decreaseLike(boardId);
    }
    public void increaseComment(long boardId) {
        repository.increaseComment(boardId);
    }
    public void decreaseComment(long boardId) {
        repository.decreaseComment(boardId);
    }
    public void decreaseCommentsByBoardId(long commentCnt, long boardId) {
        repository.decreaseCommentsByBoardId(commentCnt, boardId);
    }

    public void deleteBoard(long boardId) {
        repository.deleteBoard(boardId);
    }

    public boolean isUsable(long boardId) {return repository.isUsable(boardId);}
    public void toUnusableByBoardId(long boardId) {repository.toUnusableByBoardId(boardId);}
    public void toUnusableByUserId(long userId) {repository.toUnusableByUserId(userId);}

}
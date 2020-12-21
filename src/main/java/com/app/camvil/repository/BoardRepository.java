package com.app.camvil.repository;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.BoardsDTO;
import com.app.camvil.dto.responsedto.CampsiteCountResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {
    List<BoardsDTO> getBoards(String search, String campsiteCode, String order,
                              int limit, int offset);
    List<BoardsDTO> getBoardsContainsCode(String search, String campsiteCode, String order,
                              int limit, int offset);
    BoardDTO findLastBoardId();
    BoardDTO findBoardByBoardId(long boardId);
    List<BoardDTO> findBoardsByUserId(long userId);

    List<BoardDTO> getBoardsWithLikeCount();
    List<BoardDTO> getBoardsByCampsiteCode(String campsiteCode);

    List<CampsiteCountResponseDTO> getCampsiteCount();
    long getCommentCountByBoardId(long boardId);
    long getTotalBoardCnt(String search, String campsiteCode, String order);
    void insertBoard(BoardDTO boardDTO);
    void updateBoard(BoardDTO boardDTO);
    void increaseLike(long boardId);
    void decreaseLike(long boardId);
    void increaseComment(long boardId);
    void decreaseComment(long boardId);
    void decreaseCommentsByBoardId(long commentCnt, long boardId);

    void deleteBoard(long boardId);
}

package com.app.camvil.repository;

import com.app.camvil.dto.BoardDTO;
import com.app.camvil.dto.responsedto.CampsiteCountResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {
    BoardDTO findLastBoardId();
    BoardDTO findBoardByBoardId(int boardId);
    List<BoardDTO> findBoardsByUserId(int userId);

    List<BoardDTO> getBoards();
    List<BoardDTO> getBoardsWithLikeCount();
    List<BoardDTO> getBoardsByCampsiteCode(String campsiteCode);

    List<CampsiteCountResponseDTO> getCampsiteCount();
    int getCommentCountByBoardId(int boardId);

    void insertBoard(BoardDTO boardDTO);
    void updateBoard(BoardDTO boardDTO);
    void increaseLike(int boardId);
    void decreaseLike(int boardId);
    void increaseComment(int boardId);
    void decreaseComment(int boardId);
    void decreaseCommentsByBoardId(int commentCnt, int boardId);

    void deleteBoard(int boardId);
}

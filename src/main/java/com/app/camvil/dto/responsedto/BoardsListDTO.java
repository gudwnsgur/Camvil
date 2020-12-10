package com.app.camvil.dto.responsedto;

import java.util.List;

public class BoardsListDTO {
    private List<BoardsResponseDTO> boardsResponseDTOList;

    public BoardsListDTO (){}
    public BoardsListDTO (
            List<BoardsResponseDTO> boardsResponseDTOS
    ) { this.boardsResponseDTOList = boardsResponseDTOS;}

    public List<BoardsResponseDTO> getBoardsResponseDTOList() {
        return boardsResponseDTOList;
    }

    public void setBoardsResponseDTOList(List<BoardsResponseDTO> boardsResponseDTOList) {
        this.boardsResponseDTOList = boardsResponseDTOList;
    }
    public void addList(BoardsResponseDTO boardsResponseDTO) {
        boardsResponseDTOList.add(boardsResponseDTO);
    }
}

package com.app.camvil.repository;

import com.app.camvil.dto.ImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImageRepository {
    List<ImageDTO> findImagesByBoardId(int boardId);
    void insertImages(ImageDTO imageDTO);
    void deleteImagesByBoardId(int boardId);
}

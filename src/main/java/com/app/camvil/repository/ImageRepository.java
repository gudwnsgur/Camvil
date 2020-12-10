package com.app.camvil.repository;

import com.app.camvil.dto.ImageDTO;
import com.app.camvil.dto.ImageListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImageRepository {
    List<ImageDTO> findImagesByBoardId(long boardId);
    void insertImages(ImageDTO imageDTO);
    void deleteImagesByBoardId(long boardId);
    List<ImageListDTO> findImageListByBoardId(long boardId);
}

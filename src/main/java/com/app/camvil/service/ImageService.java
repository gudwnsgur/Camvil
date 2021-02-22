package com.app.camvil.service;

import com.app.camvil.dto.ImageDTO;
import com.app.camvil.dto.ImageListDTO;
import com.app.camvil.repository.ImageRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    private static String BASE_PATH = "C:/camvil/camvil/src/main/resources/static/images";
    @Autowired
    private ImageRepository repository;

    public String getImageNames(String encodedString) throws IOException {
        String outputFileName = UUID.randomUUID().toString() + ".jpg";
        byte[] decode = Base64.decodeBase64(encodedString);
        FileOutputStream fos = null; //null로 초기화
        try {
            File target = new File(BASE_PATH + "/" + outputFileName);
            fos = new FileOutputStream(target);
            fos.write(decode);
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) fos.close();
        }
        return outputFileName;
    }

    public String getUserImageName(String encodedString) throws IOException {
        String outputFileName = UUID.randomUUID().toString() + ".jpg";
        byte[] decode = Base64.decodeBase64(encodedString);
        FileOutputStream fos = null; //null로 초기화
        try {
            File target = new File(BASE_PATH + "/users/" + outputFileName);
            fos = new FileOutputStream(target);
            fos.write(decode);
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) fos.close();
        }
        return outputFileName;
    }

    public void insertImages(ImageDTO imageDTO) {
        repository.insertImages(imageDTO);
    }

    public List<ImageDTO> findImagesByBoardId(long boardId) {
        return repository.findImagesByBoardId(boardId);
    }
    public List<ImageListDTO> findImageListByBoardId(long boardId) {return repository.findImageListByBoardId(boardId);}

    public boolean isUsable(long imageId) {return repository.isUsable(imageId);}
    public void toUnusableByImageId(long imageId){repository.toUnusableByImageId(imageId);}
    public void toUnusableByBoardId(long boardId){repository.toUnusableByBoardId(boardId);}
}


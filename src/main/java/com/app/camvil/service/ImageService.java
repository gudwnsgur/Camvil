package com.app.camvil.service;

import com.app.camvil.dto.ImageDTO;
import com.app.camvil.dto.ImageListDTO;
import com.app.camvil.repository.ImageRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    private static String BASE_PATH = "C:/camvil/camvil/src/main/resources/images";
    private static String BASE_PROFILE_IMAGE =  "C:/camvil/camvil/src/main/resources/images/users/camvil_basic_image.jpg";
    @Autowired
    private ImageRepository repository;

    public String getBasePath() {
        return BASE_PATH;
    }
    public String getBaseProfileImage() {
        return BASE_PROFILE_IMAGE;
    }

    public String getImageNames(String encodedString) throws IOException {
        String outputFileName =  new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        byte decode[] = Base64.decodeBase64(encodedString);
        FileOutputStream fos;
        try {
            File target = new File(BASE_PATH + "/" + outputFileName);
            fos = new FileOutputStream(target);
            fos.write(decode);
            fos.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return outputFileName;
    }

    public String getUserImageName(String encodedString) throws IOException {
        String outputFileName =  new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        byte decode[] = Base64.decodeBase64(encodedString);
        FileOutputStream fos;
        try {
            File target = new File(BASE_PATH + "/users/" + outputFileName);
            fos = new FileOutputStream(target);
            fos.write(decode);
            fos.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return outputFileName;
    }

    public String getProfileImagePath(String image) throws IOException{
        String outputFileName =  new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        FileOutputStream fos;
        try {
            URL url = new URL(image);
            BufferedImage img = ImageIO.read(url);
            File target = new File(BASE_PATH + "/users/" + outputFileName);
            ImageIO.write(img, "jpg", target);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return BASE_PATH + "/users/" + outputFileName;
    }
    public void deleteImage(String imagePath, String imageName) {
        File file = new File(imagePath + "/" + imageName);
        if(file.exists()){
            file.delete();
        }
        return;
    }
    public void deleteImage(String fullPath) {
        File file = new File(fullPath);
        if(file.exists()){
            file.delete();
        }
        return;
    }

    public String getFirstImagePath(String encodedString) {
        return "return first image path";
    }

    public void insertImages(ImageDTO imageDTO) {
        repository.insertImages(imageDTO);
        return;
    }
    public List<ImageDTO> findImagesByBoardId(long boardId) {
        return repository.findImagesByBoardId(boardId);
    }
    public List<ImageListDTO> findImageListByBoardId(long boardId) {return repository.findImageListByBoardId(boardId);}
    public void deleteImagesByBoardId(long boardId) {
        repository.deleteImagesByBoardId(boardId);
    }
}


package com.app.camvil.repository;

import com.app.camvil.dto.SearchDTO;
import com.app.camvil.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {
    List<UserDTO> getUserList();
    UserDTO findUserByUserId(long userId);
    UserDTO findUserByUserSid(String userSid);
    boolean isExternalImage(long userId);
    List<SearchDTO> getSearch();
    SearchDTO findSearchBySearchContent(String searchContent);
    void insertSearchContent(String searchContent);
    void increaseSearchContent(String searchContent);
    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void updateUserToken(UserDTO user);
    void deleteUser(long userId);
}
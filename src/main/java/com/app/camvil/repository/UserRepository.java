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
    List<SearchDTO> getSearch();

    void insertUser(UserDTO user);
    void updateUser(UserDTO user);
    void updateUserToken(UserDTO user);
    void deleteUser(long userId);
}
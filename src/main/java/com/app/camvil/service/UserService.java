package com.app.camvil.service;

import com.app.camvil.dto.SearchDTO;
import com.app.camvil.dto.UserDTO;
import com.app.camvil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<UserDTO> getUserList() {
        return repository.getUserList();
    }
    public void insertUser(UserDTO user) {
        repository.insertUser(user);
    }
    public void updateUser(UserDTO user) {
        repository.updateUser(user);
    }
    public void updateUserToken(UserDTO user) {
        repository.updateUserToken(user);
    }
    public UserDTO findUserByUserId(int userId) {return repository.findUserByUserId(userId);}
    public UserDTO findUserByUserSid(String userSid) {return repository.findUserByUserSid(userSid);}

    public void deleteUser(int userId) {
        repository.deleteUser(userId);
    }

    public List<SearchDTO> getSearch() {return repository.getSearch();}

}
package com.app.camvil.controller;

import com.app.camvil.dto.UserDTO;
import com.app.camvil.service.ImageService;
import com.app.camvil.service.KakaoAPI;
import com.app.camvil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class KakaoController {
    @Autowired
    private KakaoAPI kakaoAPI;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    // login 할때 admin 확인
    @RequestMapping(value = "/login/kakao")
    public String login(@RequestParam String code, HttpSession session) throws IOException {
        String accessToken = kakaoAPI.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(accessToken);
        System.out.println(userInfo);

        UserDTO curUser = new UserDTO(userInfo.get("userSid").toString(),
                userInfo.get("userEmail").toString(),
                userInfo.get("userName").toString(),
                "NULL",
                userInfo.get("accessToken").toString()
        );


        if(userService.findUserByUserSid(userInfo.get("userSid").toString()) == null)  {
            System.out.println("NOT EXISTED USER");

            String userImagePath = imageService.getProfileImagePath(userInfo.get("userImagePath").toString());
            curUser.setUserImagePath(userImagePath);
            userService.insertUser(curUser);
        }
        else {
            System.out.println("EXISTED USER");
            userService.updateUserToken(curUser);
        }

        if(userInfo.get("userEmail") != null) {
            session.setAttribute("user_id", userInfo.get("userEmail"));
            session.setAttribute("accessToken", accessToken);
        }
        return "index";
    }

    @GetMapping(value="/logout/kakao")
    public String logout(HttpSession session) {
        kakaoAPI.kakaoLogout((String)session.getAttribute("accessToken"));

        session.removeAttribute("accessToken");
        session.removeAttribute("userSid");
        return "index";
    }
}

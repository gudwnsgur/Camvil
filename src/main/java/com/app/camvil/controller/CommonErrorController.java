package com.app.camvil.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CommonErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(ModelAndView mv, HttpServletRequest request) {
        Gson gson = new GsonBuilder().create();
        Map<String, Object> response = new HashMap<>();
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println("[STATUS] " + Integer.valueOf(status.toString()));
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            response.put("responseCode", Integer.valueOf(status.toString()));

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                response.put("responseMessage", "NOT_FOUND");
                return gson.toJson(response);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                response.put("responseMessage", "INTERNAL_SERVER_ERROR");
                return gson.toJson(response);
            } else if(statusCode == HttpStatus.CONFLICT.value()) {
                response.put("responseCode", statusCode);
                response.put("responseMessage", "CONFLICT");
                return gson.toJson(response);
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                response.put("responseMessage", "FORBIDDEN");
                return gson.toJson(response);
            } else if(statusCode == HttpStatus.BAD_GATEWAY.value()) {
                response.put("responseMessage", "BAD_GATEWAY");
                return gson.toJson(response);
            }else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                response.put("responseMessage", "BAD_REQUEST");
                return gson.toJson(response);
            }else if(statusCode == HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value()) {
                response.put("responseMessage", "BANDWIDTH_LIMIT_EXCEEDED");
                return gson.toJson(response);
            }else if(statusCode == HttpStatus.FAILED_DEPENDENCY.value()) {
                response.put("responseMessage", "FAILED_DEPENDENCY");
                return gson.toJson(response);
            }

        }
        response.put("responseMessage", "etc");
        return gson.toJson(response);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}



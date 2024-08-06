package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.CustomException.CustomException;
import com.example.backend.dto.LoginReqDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    // get all students api
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginReqDTO loginReq) {
        String token = "";
        try {
            token = authService.GenerateToken(loginReq);
        } catch (CustomException e) {
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), 401, null), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().body(new ResponseDTO().setData(token));
    }

}

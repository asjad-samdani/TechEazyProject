package com.example.backend.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.CustomException.CustomException;
import com.example.backend.constants.Role;
import com.example.backend.dto.LoginReqDTO;
import com.example.backend.dto.RegisterDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.service.impl.AuthServiceImpl;
import com.example.backend.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @Autowired
    UserServiceImpl userService;

    // get all students api
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginReqDTO loginReq) {
        String token = "";
        try {
            token = authService.GenerateToken(loginReq);
        } catch (CustomException e) {
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().body(new ResponseDTO().setData(token).setMessage("Logged in successfully"));
    }

    /**
     * @apiNote Will create only users with admin role
     * 
     * @param reqBody
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterDTO registerReq) {
        UserDTO user = new UserDTO();
        try {

            registerReq.setRole(Role.ADMIN.toString());
            user = userService.createStudent(registerReq);
        } catch (BadRequestException e) {
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO().setData(null).setMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(new ResponseDTO().setData(user).setMessage("Admin user registered!"));
    }

}

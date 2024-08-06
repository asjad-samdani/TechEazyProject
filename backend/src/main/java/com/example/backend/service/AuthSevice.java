package com.example.backend.service;

import com.example.backend.CustomException.CustomException;
import com.example.backend.dto.LoginReqDTO;

public interface AuthSevice {
    String GenerateToken(LoginReqDTO loginReq) throws CustomException;

    Boolean ValidateToken(String token);
}

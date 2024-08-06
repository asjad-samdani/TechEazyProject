package com.example.backend.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.example.backend.dto.UserDTO;
import com.example.backend.dto.RegisterDTO;

public interface UserService {
    public Object createStudent(RegisterDTO req) throws BadRequestException;

    public List<UserDTO> getAll();

    public UserDTO GetStudentByID(Integer studentId);
}

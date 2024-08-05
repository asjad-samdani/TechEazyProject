package com.example.backend.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.StudentRequestDTO;

public interface StudentService {
    public Object createStudent(StudentRequestDTO req) throws BadRequestException;

    public List<StudentDTO> getAll();
}

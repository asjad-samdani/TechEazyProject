package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.StudentRequestDTO;

public interface StudentService {
    public Object createStudent(StudentRequestDTO req);

    public List<StudentDTO> getAll();
}

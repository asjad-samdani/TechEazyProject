package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.StudentRequestDTO;
import com.example.backend.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl stuService;

    @PostMapping()
    public Object createStudent(@RequestBody StudentRequestDTO reqBody) {
        return stuService.createStudent(reqBody);
    }

    // get all students api
    @GetMapping()
    public Object getAllStudent() {
        return stuService.getAll();
    }

}

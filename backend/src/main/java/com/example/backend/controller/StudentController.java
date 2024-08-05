package com.example.backend.controller;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.ResponseDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.StudentRequestDTO;
import com.example.backend.entity.SubjectEntity;
import com.example.backend.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl stuService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createStudent(@RequestBody StudentRequestDTO reqBody) {

        StudentDTO student = new StudentDTO();

        try {
            student = stuService.createStudent(reqBody);
        } catch (BadRequestException e) {
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO().setData(null).setCode(400).setErrorMessage(e.getMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<ResponseDTO>(new ResponseDTO().setData(student).setCode(200).setErrorMessage(null),
                HttpStatus.OK);
    }

    // get all students api
    @GetMapping()
    public Object getAllStudent() {
        return stuService.getAll();
    }

}

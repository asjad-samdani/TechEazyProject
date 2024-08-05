package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.entity.SubjectEntity;
import com.example.backend.service.impl.SubjectServiceImp;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectServiceImp subService;

    @PostMapping()
    public ResponseEntity<Object> createSubject(@RequestBody List<SubjectEntity> subEntity) {

        List<SubjectEntity> subjects = new ArrayList<>();
        try {
            subjects = subService.createSubject(subEntity);
        } catch (JpaSystemException e) {
            return ResponseEntity.badRequest().body("Some or all subjects already exists");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error");
        }

        return new ResponseEntity<Object>(subjects, HttpStatus.OK);

    }

    // @GetMapping()
    // public Object getAllSubject() {
    // return subService.getAllSubject();
    // }

    @GetMapping
    public ResponseEntity<List<SubjectEntity>> getAllSubjects() {
        List<SubjectEntity> subjects = subService.getAllSubject();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}

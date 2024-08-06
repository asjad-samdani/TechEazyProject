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
import com.example.backend.constants.Role;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.SubjectEntity;
import com.example.backend.service.impl.AuthServiceImpl;
import com.example.backend.service.impl.SubjectServiceImp;
import com.example.backend.utils.RequestUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectServiceImp subService;

    @Autowired
    AuthServiceImpl authService;

    @PostMapping()
    public ResponseEntity<ResponseDTO> createSubject(@RequestBody List<SubjectEntity> subEntity,
            HttpServletRequest req) {

        UserDTO user = authService.GetTokenData(RequestUtils.GetTokenFromHeader(req));
        ResponseDTO response = new ResponseDTO();
        if (!user.getRole().equals(Role.ADMIN.toString())) {
            response.setMessage("Only admin can add subjects");
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.FORBIDDEN);
        }

        List<SubjectEntity> subjects = new ArrayList<>();

        try {
            subjects = subService.createSubject(subEntity);
            response.setData(subjects);
        } catch (JpaSystemException e) {
            response.setMessage("Some or all subjects already exists");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setMessage("Internal server error");
            return ResponseEntity.internalServerError().body(response);
        }

        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllSubjects() {
        List<SubjectEntity> subjects = subService.getAllSubject();

        ResponseDTO resp = new ResponseDTO().setData(subjects).setMessage("List of all subjects!");
        return new ResponseEntity<ResponseDTO>(resp, HttpStatus.OK);
    }
}

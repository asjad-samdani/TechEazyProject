package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.backend.dto.UserDTO;
import com.example.backend.constants.Role;
import com.example.backend.dto.RegisterDTO;
import com.example.backend.service.impl.AuthServiceImpl;
import com.example.backend.service.impl.UserServiceImpl;
import com.example.backend.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/student")
public class UserController {

    @Autowired
    private UserServiceImpl stuService;

    @Autowired
    AuthServiceImpl authService;

    /**
     * @apiNote Will create only users with student role
     * 
     * @param reqBody
     * @return
     */
    @PostMapping()
    public ResponseEntity<ResponseDTO> createStudent(@RequestBody RegisterDTO reqBody, HttpServletRequest req) {
        UserDTO sessionUser = authService.GetTokenData(RequestUtils.GetTokenFromHeader(req));
        ResponseDTO response = new ResponseDTO();
        if (!sessionUser.getRole().equals(Role.ADMIN.toString())) {
            response.setMessage("Only admin can create student type user");
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.FORBIDDEN);
        }

        UserDTO user = new UserDTO();

        try {
            reqBody.setRole(Role.STUDENT.toString());
            user = stuService.createStudent(reqBody);
        } catch (BadRequestException e) {
            return new ResponseEntity<ResponseDTO>(
                    new ResponseDTO().setData(null).setMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResponseDTO>(new ResponseDTO().setData(user).setMessage("Student user created!"),
                HttpStatus.OK);
    }

    // get all students api
    @GetMapping()
    public ResponseEntity<ResponseDTO> getAllStudent(HttpServletRequest req) {

        UserDTO user = authService.GetTokenData(RequestUtils.GetTokenFromHeader(req));
        ResponseDTO response = new ResponseDTO();

        try {
            List<UserDTO> students = new ArrayList<>();

            // get all students data only if admin is calling the API else return the self
            // data only
            if (user.getRole().equals(Role.ADMIN.toString())) {
                students = stuService.getAll();
            } else {
                user = stuService.GetStudentByID(user.getId());
                students.add(user);
            }

            response.setData(students);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

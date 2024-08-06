package com.example.backend.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.CustomException.CustomException;
import com.example.backend.dto.LoginReqDTO;
import com.example.backend.entity.Session;
import com.example.backend.entity.StudentEntity;
import com.example.backend.repository.SessionRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.AuthSevice;

@Service
public class AuthServiceImpl implements AuthSevice {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public String GenerateToken(LoginReqDTO loginReq) throws CustomException {

        // 1. Validate email

        StudentEntity student = studentRepository.findByEmail(loginReq.getEmail());

        if (student == null || student.getId() == null) {
            throw new CustomException("Invalid email", 401);
        }

        // 2. Validate password

        if (!Objects.equals(loginReq.getPassword(), student.getPassword())) {
            throw new CustomException("Invalid password", 401);
        }

        // 3. Generate new token
        Session session = new Session();
        session.setUserId(student.getId());
        session.setToken(UUID.randomUUID().toString());

        Instant instant = Instant.now();
        instant = instant.plus(2, ChronoUnit.HOURS);
        Long timeStampMili = instant.toEpochMilli();
        session.setExpiration(timeStampMili);

        sessionRepository.save(session);

        return session.getToken();
    }

    @Override
    public Boolean ValidateToken(String token) {

        // 1. Query the DB for the token
        Session session = sessionRepository.findByToken(token);
        if (session == null || session.getId() == null) {
            return false;
        }

        // 2. Check for expiry
        Instant expiry = Instant.ofEpochMilli(session.getExpiration());
        if (expiry.isBefore(Instant.now())) {
            // 3. if expired delete from db
            sessionRepository.delete(session);
            return false;
        }

        return true;
    }

}

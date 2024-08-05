package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session findByUserId(Integer userId);

    Session findByToken(String token);
}

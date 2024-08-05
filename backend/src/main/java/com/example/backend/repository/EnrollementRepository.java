package com.example.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.backend.entity.Enrollment;

public interface EnrollementRepository extends CrudRepository<Enrollment, Integer> {

}

package com.example.backend.service;

import java.util.List;
import com.example.backend.entity.SubjectEntity;

public interface SubjectService {

    public List<SubjectEntity> createSubject(List<SubjectEntity> subjectEntities);

    public List<SubjectEntity> getAllSubject();
}

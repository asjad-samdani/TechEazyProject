package com.example.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.SubjectEntity;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.SubjectService;

@Service
public class SubjectServiceImp implements SubjectService {

    @Autowired
    private SubjectRepository subrepo;

    @Override
    public List<SubjectEntity> createSubject(List<SubjectEntity> subjectEntities) {
        return subrepo.saveAll(subjectEntities);
    }

    @Override
    public List<SubjectEntity> getAllSubject() {
        return subrepo.findAll();

    }

}

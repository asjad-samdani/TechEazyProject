package com.example.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.entity.SubjectEntity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    int id;
    String name;
    String address;
    String email;
    String role;

    @NonNull
    List<SubjectEntity> subjects = new ArrayList<>();
}

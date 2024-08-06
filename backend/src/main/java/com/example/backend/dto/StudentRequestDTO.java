package com.example.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.entity.StudentEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
    String name;
    String address;
    List<Integer> subject_ids = new ArrayList<>();
    String email;
    String password;
    String role;

    public StudentEntity toStudentEntity() {
        StudentEntity studententity = new StudentEntity();
        studententity.setName(this.name);
        studententity.setAddress(this.address);
        studententity.setEmail(this.email);
        studententity.setPassword(this.password);
        studententity.setRole(this.role);
        return studententity;
    }

    // StudentDTO -> StudentRequestDTO

    // public StudentRequestDTO toStudentDTO(StudentDTO student) {
    // StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
    // studentRequestDTO.setName(student.getName());
    // studentRequestDTO.setAddress(student.getAddress());
    // studentRequestDTO.setEmail(student.getEmail());
    // return studentRequestDTO;

    // }

}

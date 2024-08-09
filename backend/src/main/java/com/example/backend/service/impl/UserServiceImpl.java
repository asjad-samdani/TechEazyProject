package com.example.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.dto.UserDTO;
import com.example.backend.CustomException.CustomException;
import com.example.backend.dto.RegisterDTO;
import com.example.backend.entity.Enrollment;
import com.example.backend.entity.UserEntity;
import com.example.backend.entity.SubjectEntity;
import com.example.backend.repository.EnrollementRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository studrepo;
    @Autowired
    private EnrollementRepository enrollRepository;

    @Override
    public UserDTO createStudent(RegisterDTO req) throws BadRequestException {

        UserEntity password = studrepo.findByEmail(req.getEmail());
        if (password != null) {
            throw new BadRequestException("Email already in use");
        }

        if (Objects.equals(req.getRole(), "admin") && req.getSubject_ids().size() > 0)

        {
            throw new BadRequestException("Admin cannot be enrolled for subjects");
        }
        // create the student -> student id
        UserEntity userEntity = req.toUserEntity();

        userEntity = studrepo.save(userEntity);

        // create enrollment for all the subject_ids
        List<Enrollment> enrollments = new ArrayList<>();

        for (Integer subjectId : req.getSubject_ids()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(userEntity);
            SubjectEntity subject = new SubjectEntity();
            subject.setId(subjectId);
            enrollment.setSubject(subject);
            enrollments.add(enrollment);
        }

        enrollRepository.saveAll(enrollments);

        return

        GetStudentByID(userEntity.getId());
    }

    @Override
    public UserDTO GetStudentByID(Integer studentId) {
        UserEntity student = studrepo.findById(studentId).get();
        UserDTO studentDTO = ToStudentDTO(student);
        return studentDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> students = studrepo.findAll();
        List<UserDTO> studentDTOs = new ArrayList<>();
        for (UserEntity studentEntity : students) {
            studentDTOs.add(ToStudentDTO(studentEntity));
        }
        return studentDTOs;
    }

    private UserDTO ToStudentDTO(UserEntity entity) {
        UserDTO studDto = new UserDTO();
        studDto.setId(entity.getId());
        studDto.setName(entity.getName());
        studDto.setAddress(entity.getAddress());
        studDto.setEmail(entity.getEmail());
        studDto.setRole(entity.getRole());

        List<SubjectEntity> subjects = new ArrayList<>();
        for (Enrollment enrollment : entity.getEnrollments()) {
            subjects.add(enrollment.getSubject());
        }
        studDto.setSubjects(subjects);
        return studDto;
    }

}

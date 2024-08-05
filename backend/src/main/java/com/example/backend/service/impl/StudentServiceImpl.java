package com.example.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.StudentRequestDTO;
import com.example.backend.entity.Enrollment;
import com.example.backend.entity.StudentEntity;
import com.example.backend.entity.SubjectEntity;
import com.example.backend.repository.EnrollementRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studrepo;
    @Autowired
    private EnrollementRepository enrollRepository;

    // StudentEntity toStudentEntity(StudentRequestDTO req) {
    // StudentEntity studententity = new StudentEntity();
    // studententity.setName(req.getName());
    // studententity.setAddress(req.getAddress());
    // studententity.setEmail(req.getEmail());
    // studententity.setPassword(req.getPassword());
    // studententity.setRole(req.getRole());

    // return studententity;
    // }

    @Override
    public StudentDTO createStudent(StudentRequestDTO req) throws BadRequestException {

        if (Objects.equals(req.getRole(), "admin") && req.getSubject_ids().size() > 0) {
            throw new BadRequestException("Admin cannot be enrolled for subjects");
        }
        // create the student -> student id
        StudentEntity studententity = req.toStudentEntity();

        studententity = studrepo.save(studententity);

        // create enrollment for all the subject_ids
        List<Enrollment> enrollments = new ArrayList<>();

        for (Integer subjectId : req.getSubject_ids()) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(studententity);

            SubjectEntity subject = new SubjectEntity();
            subject.setId(subjectId);
            enrollment.setSubject(subject);

            enrollments.add(enrollment);
        }

        enrollRepository.saveAll(enrollments);

        return GetStudentByID(studententity.getId());
    }

    public StudentDTO GetStudentByID(Integer studentId) {
        StudentEntity student = studrepo.findById(studentId).get();
        StudentDTO studentDTO = ToStudentDTO(student);
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentEntity> students = studrepo.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (StudentEntity studentEntity : students) {
            studentDTOs.add(ToStudentDTO(studentEntity));
        }
        return studentDTOs;
    }

    private StudentDTO ToStudentDTO(StudentEntity entity) {
        StudentDTO studDto = new StudentDTO();
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

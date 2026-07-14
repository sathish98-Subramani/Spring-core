package com.studentmanagement.service.Impl;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.util.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Value("${student.not.found}")
    private String studentNotFound;

    @Override
    public void save(StudentDTO studentDTO) {
        Student student=studentMapper.toEntity(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public void update(int studentId, String courseName) {

    }

    @Override
    public void deleteById(Integer studentId) {

    }

    @Override
    public StudentDTO findById(Integer studentId) {
      return null;
    }

    @Override
    public List<StudentDTO> findAll() {
        return List.of();
    }
}

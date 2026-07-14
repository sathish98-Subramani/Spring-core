package com.studentmanagement.service.Impl;

import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.util.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Student student = studentMapper.toEntity(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public void update(int studentId, String courseName) {
        studentRepository.update(studentId, courseName);
    }

    @Override
    public void deleteById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            studentRepository.deleteById(studentId);
        } else {
            throw new StudentNotFoundException(studentNotFound);
        }
    }

    @Override
    public StudentDTO findById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return studentMapper.toDto(student.get());
        }
        throw new StudentNotFoundException(studentNotFound);
    }

    @Override
    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }
}
package com.studentmanagement.repository.Impl;

import com.studentmanagement.enums.Gender;
import com.studentmanagement.enums.Specialization;
import com.studentmanagement.model.Address;
import com.studentmanagement.model.Course;
import com.studentmanagement.enums.CourseType;
import com.studentmanagement.model.Department;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class   StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Student student) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    @Override
    public void update(int studentId, String courseName) {

    }

    @Override
    public void deleteById(Integer studentId) {
    }

    @Override
    public Optional<Student> findById(Integer studentId) {
     return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}

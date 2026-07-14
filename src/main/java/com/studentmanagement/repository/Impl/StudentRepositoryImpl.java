package com.studentmanagement.repository.Impl;

import com.studentmanagement.model.Course;
import com.studentmanagement.model.Department;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Department department = session.createQuery(
                            "from Department where name=:name", Department.class)
                    .setParameter("name", student.getDepartment().getName())
                    .uniqueResult();
            if (department == null) {
                department = student.getDepartment();
                session.persist(department);
            }
            student.setDepartment(department);
            session.persist(student);
            tx.commit();
            System.out.println("Student Details Saved Successfully");
        }
    }

    @Override
    public void update(int studentId, String courseName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            if (student != null) {
                for (Course course : student.getCourse()) {
                    course.setCourseName(courseName);
                }
                session.merge(student);
                System.out.println("Course Updated Successfully");
            } else {
                System.out.println("Student Not Found");
            }
            tx.commit();
        }
    }

    @Override
    public void deleteById(Integer studentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            if (student != null) {
                session.remove(student);
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Student Not Found");
            }
            tx.commit();
        }
    }

    @Override
    public Optional<Student> findById(Integer studentId) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.find(Student.class, studentId);
            return Optional.ofNullable(student);
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}
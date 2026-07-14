package com.studentmanagement;

import com.studentmanagement.config.AppConfig;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.util.InputUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class App {

    private final StudentService studentService;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            App app = new App(context.getBean(StudentService.class));
            app.run();
        }
    }

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            do {
                int choice = InputUtil.acceptMenuOption(sc);
                switch (choice) {
                    case 1:
                        StudentDTO studentDTO = InputUtil.acceptStudentDetailsToSave(sc);
                        studentService.save(studentDTO);
                        break;

                    case 2:
                        int studentId = InputUtil.acceptStudentIdToOperate(sc);
                        try {
                            StudentDTO student = studentService.findById(studentId);
                            System.out.println(student);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        studentId = InputUtil.acceptStudentIdToOperate(sc);
                        String courseName = InputUtil.acceptCourseDetailsToUpdate(sc);
                        try {
                            studentService.update(studentId, courseName);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        studentId = InputUtil.acceptStudentIdToOperate(sc);
                        try {
                            studentService.deleteById(studentId);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 5:
                        List<StudentDTO> students = studentService.findAll();
                        if (students.isEmpty()) {
                            System.out.println("No Student Records Found.");
                        } else {
                            students.forEach(System.out::println);
                        }
                        break;

                    default:

                        System.out.println("Invalid Option");
                }

            } while (InputUtil.wantToContinue(sc));
        }
    }
}
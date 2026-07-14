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
   public static void main(String[]args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)){
           // StudentService studentService =context.getBean(StudentService.class);
            App app = new App(context.getBean(StudentService.class));
            app.run();
        }
    }
    public void run(){
        try(Scanner sc = new Scanner(System.in)){
            do{
                int choice = InputUtil.acceptMenuOption(sc);
                switch (choice){
                    case 1:
                        StudentDTO studentDTO=InputUtil.acceptStudentDetailsToSave(sc);
                        studentService.save(studentDTO);
                        System.out.println("Student Details Saved Successfully");
                        break;
                    default:
                        System.out.println("Invalid Option");
                }

            }while (InputUtil.wantToContinue(sc));
        }
    }
}

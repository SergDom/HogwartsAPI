package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentService;

import java.util.Collection;

@RestController
@RequestMapping ("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity <Student> createStudent (@RequestBody Student student){
        Student std = studentService.add(student);
        if (std == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(std);
    }

    @GetMapping ("{id}")
    public ResponseEntity <Student> getStudent (@PathVariable long id){
        Student student = studentService.get(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity <Student> updateStudent (@RequestBody Student student){
        Student std = studentService.update(student);
        if (std == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(std);}

    @DeleteMapping ("{id}")
    public ResponseEntity <Student> deleteStudent (@PathVariable long id){
        Student student = studentService.delete(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping ("/age/{age}")
    public Collection <Student> ageFilter (@PathVariable int age) {
        return studentService.ageFilter(age);
    }
}

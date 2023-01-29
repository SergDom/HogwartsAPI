package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student std = studentService.add(student);
        if (std == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(std);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.get(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student std = studentService.update(student);
        if (std == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(std);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        Student student = studentService.get(id);
        if (student == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            studentService.delete(id);
            ResponseEntity.ok().build();
        }
    }

    @GetMapping("/age/{age}")
    public List<Student> ageFilter(@PathVariable int age) {
        return studentService.ageFilter(age);
    }


    @GetMapping("/age-between")
    public ResponseEntity<Collection<Student>> findStudentByAgeBetween (@RequestParam int min,
                                                                        @RequestParam int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping ("requestfromfaculty/{id}")
    public ResponseEntity <Collection<Student>> getStudentsFromFaculty (@PathVariable long id) {
        Collection<Student> student = studentService.getStudentsFromTheFaculty(id);
        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student); }
}

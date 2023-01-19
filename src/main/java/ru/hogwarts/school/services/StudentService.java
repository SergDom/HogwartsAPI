package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {

private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
            return studentRepository.save(student);
    }

    public Student get(long id) {

        return studentRepository.findById(id).get();
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {

        studentRepository.deleteById(id);
    }

    public List<Student> ageFilter (int age){
       return studentRepository.findAll().stream().filter(e -> e.getAge() == age).toList();

    }
}

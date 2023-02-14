package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student get(long id) {
        logger.info("Was invoked method for find student by id");
        return studentRepository.findById(id).orElse(null);
    }

    public Student update(Student student) {
        logger.info("Was invoked method for create update student data");
        return studentRepository.save(student);
    }

    public void delete(long id) {
        logger.info("Was invoked method to delete a student");
        studentRepository.deleteById(id);
    }

    public List<Student> ageFilter(int age) {
        logger.info("Was invoked method to find a student by age");
        return studentRepository.findAll().stream().filter(e -> e.getAge() == age).toList();

    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method to find a student by age range");
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStudentsFromTheFaculty(long id) {
        logger.info("Was invoked method to find student's faculty by id");
        return studentRepository.findByFacultyId(id);
    }

    public Integer getStudentCount() {
        logger.info("Was invoked method to count all students in database");
        return studentRepository.getStudentCount();
    }

    public Double getAverageStudentAge() {
        logger.info("Was invoked method to get average age all students");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method to get last five students in database");
        return studentRepository.getLastFiveStudents();
    }

    public List <String> getNamesStudentsBeginsWithA () {
        logger.info("Was invoked method to get list of students with names begins 'A'");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .sorted()
                .filter(e->e.charAt(0) == 'А' || e.charAt(0) == 'а')
                .map(String::toUpperCase)
                .toList();
    }
}

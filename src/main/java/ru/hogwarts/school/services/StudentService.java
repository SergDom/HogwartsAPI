package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {


    private final HashMap<Long, Student> students = new HashMap<>();
    private long idCount = 0;

    public Student add(Student student) {
        if (students.containsValue(student)){
            return null;
        }
        student.setId(idCount++);
        students.put(idCount, student);
        return student;
    }

    public Student get(long id) {

        return students.get(id);
    }

    public Student update(long id, Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        } else {
            return null;
        }
    }

    public Student delete(long id) {
        return students.remove(id);
    }

    public Collection<Student> ageFilter (int age){
        return students.values().stream().filter(e->e.getAge()==age).toList();
    }
}

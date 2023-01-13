package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long idCount = 0;


    public Faculty add(Faculty faculty) {
        if (faculties.containsValue(faculty)){
            return null;
        }
        faculty.setId(idCount++);
        faculties.put(idCount, faculty);
        return faculty;
    }

    public Faculty get(long id) {
        return faculties.get(id);
    }

    public Faculty update (Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        } else {
            return null;
        }
    }

    public Faculty delete(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> colorFilter (String color){
        return faculties.values().stream().filter(e->e.getColor().equals(color)).toList();
    }
}

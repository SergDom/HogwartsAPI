package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        logger.info("Was invoked method for find faculty by id");
        return facultyRepository.findById(id).get();
    }

    public Faculty update(Faculty faculty) {
        logger.info("Was invoked method for create update faculty data");
        return facultyRepository.save(faculty);
    }

    public void delete(long id) {
        logger.info("Was invoked method to delete a faculty");
        facultyRepository.deleteById(id);
    }


    public List<Faculty> colorFilter(String color) {
        logger.info("Was invoked method to find a faculty by color");
        return facultyRepository.findAll().stream().filter(e -> e.getColor().equals(color)).toList();
    }
    public Collection<Faculty> findByNameOrColor (String name, String color){
        logger.info("Was invoked method to find a faculty by name or color");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Collection<Faculty> getFacultyByStudentId (long id){
        logger.info("Was invoked method to find faculty by student's id");
        return  facultyRepository.findFacultyByStudentsId(id);
    }

    public String getTheLongestName (){
        logger.info("Was invoked method to find faculty with the longest name");
        return facultyRepository.findAll()
                .stream()
                .max(Comparator.comparing(Faculty::getName))
                .map(Faculty::getName)
                .orElseThrow();
    }
}

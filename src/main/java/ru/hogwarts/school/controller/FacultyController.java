package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.services.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty (@RequestBody Faculty faculty){
        Faculty fac = facultyService.add(faculty);
        if (fac == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(fac);
    }

    @GetMapping("{id}")
    public ResponseEntity <Faculty> getFaculty (@PathVariable long id){
        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity <Faculty> updateFaculty (@RequestBody Faculty faculty){
        Faculty fuc = facultyService.update(faculty);
        if (fuc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fuc);}

    @DeleteMapping ("{id}")
    public ResponseEntity <Faculty> deleteFaculty (@PathVariable long id){
        Faculty faculty = facultyService.delete(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping ("/color/{color}")
    public Collection<Faculty> colorFilter (@PathVariable String color) {

        return facultyService.colorFilter(color);
    }
}

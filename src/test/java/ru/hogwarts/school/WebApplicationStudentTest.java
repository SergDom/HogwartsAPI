package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebApplicationStudentTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Fedor");
        student.setAge(30);

        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotEmpty();
    }

    @Test
    void getStudentTest() throws Exception {
        int id = 2;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class))
                .isNotNull();
    }

    @Test
    void editStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Ivan");
        student.setAge(25);
        restTemplate.put("http://localhost:" + port + "/student", student, String.class);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotEqualTo(student);
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Fedor");
        student.setAge(30);
        restTemplate.put("http://localhost:" + port + "/student", student, String.class);
        restTemplate.delete("http://localhost:" + port + "/student/1", student, String.class);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotNull();
    }

    @Test
    void ageFilterTest() throws Exception {
        long age = 10;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/" + age, String.class)).isNotNull();
    }

    @Test
    void findStudentByAgeBetweenTest() throws Exception {
        long age1 = 20;
        long age2 = 30;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age-between/", String.class, age1, age2))
                .isNotNull();
    }

    @Test
    void getFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setName("whateverFaculty");
        faculty.setColor("green");
        restTemplate.put("http://localhost:" + port + "/faculty", faculty, String.class);

        Student student = new Student();
        student.setId(1L);
        student.setName("Ivan");
        student.setAge(23);
        studentController.getStudentsFromFaculty(faculty.getId());
        restTemplate.put("http://localhost:" + port + "/student", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/1", String.class))
                .isNotNull();

    }
}

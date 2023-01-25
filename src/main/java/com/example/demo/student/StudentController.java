package com.example.demo.student;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")


public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void  registerNewStudent(@RequestBody Student student) {
       studentService.addNewStudent(student);
    }

    @PutMapping(path = "{id}")
    public void  updateStudent(@PathVariable("id") Long id, @PathParam("name") String name, @PathParam("email") String email, @PathParam("dob") LocalDate dob) {
        studentService.updateStudent(id, name, email, dob);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }
}

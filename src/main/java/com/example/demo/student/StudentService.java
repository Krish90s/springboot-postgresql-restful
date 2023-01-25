package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("student already registered");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean studentExist = studentRepository.existsById(id);
        if (!studentExist){
            throw new IllegalStateException("student with the id " + id + " doesn't exist.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email, LocalDate dob) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student with the id " + id + " doesn't exist."));

        if(name != null && name.length() > 0){
            student.setName(name);
        }

        if(email != null && email.length() > 0){
            Optional<Student> studentOptional =  studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent()){
                throw new IllegalStateException("student already registered");
            }

            student.setEmail(email);
        }

        if(dob != null){
            student.setDob(dob);
        }

    }
}

package com.example.hibernate_test.controller;

import com.example.hibernate_test.model.Course;
import com.example.hibernate_test.model.Student;
import com.example.hibernate_test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/get/{id}")
    public Student getStudentById(@PathVariable Long id)
    {
        Student student = studentRepository.getStudentById(id);
        student.setCourseSet(null);
        return student;
    }

    @GetMapping(path = "/getCourseBy/{id}")
    public List<Course> getCourseListById(@PathVariable Long id)
    {
        List<Course> courseList = studentRepository.getCourseListById(id);
        courseList.forEach(e -> e.setStudentSet(null));
        return courseList;
    }

    @GetMapping(path = "/getAll")
    public List<Student> getAllStudents()
    {
        List<Student> studentList =  studentRepository.getAllStudent();
        studentList.forEach(e -> e.setCourseSet(null));
        return studentList;
    }

    @PostMapping(path = "/add")
    public String addStudent(@RequestBody Student student)
    {
        return studentRepository.addStudent(student);
    }

    @PutMapping(path = "/update")
    public String updateStudent(@RequestBody Student student)
    {
        return studentRepository.updateStudent(student);
    }

    @PutMapping(path = "/addCourse")
    public String addCourse(Long student_id, Long course_id)
    {
        return studentRepository.addCourse(student_id, course_id);
    }

    @PutMapping(path = "/removeCourse")
    public String removeCourse(Long student_id, Long course_id)
    {
        return studentRepository.removeCourse(student_id, course_id);
    }

    @DeleteMapping(path = "/delete")
    public String deleteStudent(Long student_id)
    {
        return studentRepository.deleteStudent(student_id);
    }
}

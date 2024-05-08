package com.example.hibernate_test.controller;

import com.example.hibernate_test.model.Course;
import com.example.hibernate_test.model.Employee;
import com.example.hibernate_test.model.Student;
import com.example.hibernate_test.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController
{
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "/get/{id}")
    public Course getCourseById(@PathVariable Long id)
    {
        Course course = courseRepository.getCourseById(id);
        course.setStudentSet(null);
        return course;
    }

    @GetMapping(path = "/getStudentsBy/{id}")
    public List<Student> getStudentListById(@PathVariable Long id)
    {
        List<Student> studentList = courseRepository.getStudentListById(id);
        studentList.forEach(e -> e.setCourseSet(null));
        return studentList;
    }

    @GetMapping(path = "/getAll")
    public List<Course> getAllCourse()
    {
        List<Course> courseList = courseRepository.getAllCourse();
        courseList.forEach(e -> e.setStudentSet(null));
        return courseList;
    }

    @PostMapping(path = "/add")
    public String addCourse(@RequestBody Course course)
    {
        return courseRepository.addCourse(course);
    }

    @PutMapping(path = "/update")
    public String updateCourse(@RequestBody Course course)
    {
        return courseRepository.updateCourse(course);
    }

    @DeleteMapping(path = "/delete")
    public String deleteCourse(Long course_id)
    {
        return courseRepository.deleteCourse(course_id);
    }
}

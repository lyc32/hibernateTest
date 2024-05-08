package com.example.hibernate_test.controller;

import java.util.*;
import com.example.hibernate_test.model.Department;
import com.example.hibernate_test.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping(path = "/getAll")
    public List<Department> getAllDepartments()
    {
        List<Department> departmentList = departmentRepository.getAllDepartment();
        departmentList.forEach(e -> e.setEmployeeSet(null));
        return departmentList;
    }

    @PostMapping(path = "/add")
    public String addDepartment(String department_name)
    {
        return departmentRepository.addDepartment(department_name);
    }

    @PutMapping(path = "/update")
    public String updateDepartment(@RequestBody Department department)
    {
        return departmentRepository.updateDepartment(department);
    }

    @DeleteMapping(path = "/delete")
    public String deleteDepartment(Long department_id)
    {
        return departmentRepository.deleteDepartment(department_id);
    }

}

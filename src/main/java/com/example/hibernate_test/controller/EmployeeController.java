package com.example.hibernate_test.controller;

import java.nio.file.Path;
import java.util.*;
import com.example.hibernate_test.model.Employee;
import com.example.hibernate_test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(path = "/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id)
    {
        Employee employee = employeeRepository.getEmployee(id);
        employee.getDepartment().setEmployeeSet(null);
        return employee;
    }

    @GetMapping(path = "/getAll")
    public List<Employee> getAllEmployee()
    {
        List<Employee> list = employeeRepository.getAllEmployee();
        list.stream().forEach(e -> e.getDepartment().setEmployeeSet(null));
        return list;
    }

    @GetMapping(path = "/getSalaryFrom/{min}/To/{max}")
    public List<Employee> getEmployeesBySalary(@PathVariable Double min, @PathVariable Double max)
    {
        List<Employee> list = employeeRepository.getEmployeesBySalary(min, max);
        list.stream().forEach(e -> e.getDepartment().setEmployeeSet(null));
        return list;
    }

    @PostMapping(path = "/add")
    public String addEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.addEmployee(employee);
    }

    @PutMapping(path = "/update")
    public String updateEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.updateEmployee(employee);
    }

    @PutMapping(path = "/addDepartment")
    public String updateEmployee(Long department_id, Long employee_id)
    {
        return employeeRepository.addDepartment(department_id, employee_id);
    }

    @DeleteMapping(path = "/delete")
    public String deleteEmployee(Long employee_id)
    {
        return employeeRepository.deleteEmployee(employee_id);
    }

}

package com.example.hibernate_test.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Department")
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long department_id;
    @Column(name = "department_name")
    private String department_name;

    @OneToMany(mappedBy = "employee_id")
    private Set<Employee> employeeSet;

    public Department()
    { }

    public Department(Long department_id, String department_name)
    {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long id) {
        this.department_id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department) {
        this.department_name = department;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public String toString()
    {
        return "Department{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}

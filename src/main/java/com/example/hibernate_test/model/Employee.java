package com.example.hibernate_test.model;

import jakarta.persistence.*;

@Entity
@Table(name="Employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name = "employee_name")
    private String employee_name;

    @Column(name = "employee_salary")
    private Double employee_salary;


    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    public Employee()
    {}

    public Employee(Long employee_id, String employee_name, Double employee_salary, Department department) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.department = department;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long id) {
        this.employee_id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String name) {
        this.employee_name = name;
    }

    public Double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Double salary) {
        this.employee_salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", department=" + department.getDepartment_name() +
                '}';
    }
}

package com.example.hibernate_test.repository;

import com.example.hibernate_test.model.Department;
import com.example.hibernate_test.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository
{
    private static Session session = null;

    public List<Employee> getAllEmployee()
    {
        Session session  = getSession();
        return session.createQuery("FROM Employee", Employee.class).getResultList();
    }


    public List<Employee> getEmployeesBySalary(Double min, Double max)
    {
        Session session  = getSession();
        return session.createQuery("FROM Employee WHERE employee_salary BETWEEN :min AND :max ")
                .setParameter("min", min)
                .setParameter("max", max)
                .list();
    }

    public Employee getEmployee(Long employee_id)
    {
        Session session  = getSession();
        return session.get(Employee.class, employee_id);
    }

    public String addEmployee(Employee employee)
    {
        Session session  = getSession();
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
        return "success";
    }

    public String updateEmployee(Employee employee)
    {
        Session session  = getSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getEmployee_id());
        if(e == null)
        {
            return "update employee failed:\nbecause this employee has been deleted.";
        }
        e.setEmployee_name(employee.getEmployee_name());
        e.setEmployee_salary(employee.getEmployee_salary());
        session.merge(e);
        session.getTransaction().commit();
        return "success";
    }

    public String addDepartment(Long department_id, Long employee_id)
    {
        Session session  = getSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, employee_id);
        if(employee == null)
        {
            return "Can not find Employee";
        }

        Department department = DepartmentRepository.getSession().get(Department.class, department_id);
        if(department == null)
        {
            employee.setDepartment(null);
            return "success remove department info from employee [" + employee.getEmployee_name() + "].";
        }

        employee.setDepartment(department);
        session.merge(employee);
        session.getTransaction().commit();
        return "success add new department [" + department.getDepartment_name() +"] to employee [" + employee.getEmployee_name() + "].";
    }

    public String deleteEmployee(Long employee_id)
    {
        Session session  = getSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, employee_id);
        if(employee != null)
        {
            session.delete(employee);
            session.getTransaction().commit();
            return "delete department successful:\n" + employee;
        }
        else
        {
            return "delete employee failed:\nthis employee has already deleted.";
        }
    }

    public static Session getSession()
    {
        if(session != null)
        {
            return session;
        }
        else
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Employee.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();
        }
    }
}

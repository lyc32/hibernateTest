package com.example.hibernate_test.repository;

import java.util.*;
import com.example.hibernate_test.model.Department;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository
{
    private static Session session = null;

    public List<Department> getAllDepartment()
    {
        Session session  = getSession();
        return session.createQuery("FROM Department", Department.class).getResultList();
    }

    public String addDepartment(String department)
    {
        Session session  = getSession();
        session.beginTransaction();
        session.persist(new Department(null, department));
        session.getTransaction().commit();
        return "success";
    }

    public String updateDepartment(Department department)
    {
        Session session  = getSession();;
        session.beginTransaction();
        session.merge(department);
        session.getTransaction().commit();
        return "success";
    }

    public String deleteDepartment(Long department_id)
    {
        Session session  = getSession();;
        session.beginTransaction();
        Department department = session.get(Department.class, department_id);
        if(department != null)
        {
            if(department.getEmployeeSet().size() > 0)
            {
                return "delete department failed:\nYou can not delete this department, because employeeSet is not null.";
            }
            else
            {
                session.delete(department);
                session.getTransaction().commit();
                return "delete department successful:\n" + department.toString();
            }
        }
        else
        {
            return "delete department failed:\nthis department has already deleted.";
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
            configuration.addAnnotatedClass(Department.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();
        }
    }
}

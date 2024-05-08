package com.example.hibernate_test.repository;

import com.example.hibernate_test.model.Course;
import com.example.hibernate_test.model.Department;
import com.example.hibernate_test.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public class CourseRepository
{
    private static Session session = null;


    public Course getCourseById(Long course_id)
    {
        Session session  = getSession();
        Course course = session.get(Course.class, course_id);
        return course;
    }

    public List<Student> getStudentListById(Long course_id)
    {
        Session session  = getSession();
        Course course = session.get(Course.class, course_id);
        if(course != null)
        {
            return course.getStudentSet().stream().toList();
        }
        else
        {
            return null;
        }
    }

    public List<Course> getAllCourse()
    {
        Session session  = getSession();
        return session.createQuery("FROM Course", Course.class).getResultList();
    }

    public String addCourse(Course course)
    {
        Session session  = getSession();
        session.beginTransaction();
        session.persist(course);
        session.getTransaction().commit();
        return "Add Course Successful";
    }

    public String updateCourse(Course course)
    {
        Session session  = getSession();;
        session.beginTransaction();
        Course c = session.get(Course.class, course.getCourse_id());
        c.setCourse_name(course.getCourse_name());
        session.merge(c);
        session.getTransaction().commit();
        return "Update Course Successful";
    }

    public String deleteCourse(Long course_id)
    {
        Session session  = getSession();;
        session.beginTransaction();
        Course course = session.get(Course.class, course_id);
        if(course != null)
        {
            if(course.getStudentSet().size() > 0)
            {
                return "delete course failed:\nThere are still some students in this course.";
            }
            else
            {
                session.delete(course);
                session.getTransaction().commit();
                return "delete course successful:\n" + course.toString();
            }
        }
        else
        {
            return "delete course failed:\nthis course has already deleted.";
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
            configuration.addAnnotatedClass(Course.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();
        }
    }

}

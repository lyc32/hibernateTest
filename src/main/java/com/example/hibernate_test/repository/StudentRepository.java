package com.example.hibernate_test.repository;

import com.example.hibernate_test.model.Course;
import com.example.hibernate_test.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.util.List;

@Repository
public class StudentRepository
{
    private static Session session = null;


    public Student getStudentById(Long student_id)
    {
        Session session  = getSession();
        Student student = session.get(Student.class, student_id);
        return student;
    }

    public List<Course> getCourseListById(Long student_id)
    {
        Session session  = getSession();
        Student student = session.get(Student.class, student_id);
        if(student != null)
        {
            return student.getCourseSet().stream().toList();
        }
        else
        {
            return null;
        }
    }

    public List<Student> getAllStudent()
    {
        Session session  = getSession();
        return session.createQuery("FROM Student", Student.class).getResultList();
    }

    public String addStudent(Student student)
    {
        Session session  = getSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        return "Add Student Successful";
    }

    public String updateStudent(Student student)
    {
        Session session  = getSession();;
        session.beginTransaction();
        Student s = session.get(Student.class, student.getStudent_id());
        s.setStudent_name(student.getStudent_name());
        session.merge(s);
        session.getTransaction().commit();
        return "Update Student Successful";
    }

    public String addCourse(Long student_id, Long course_id)
    {
        Session session = getSession();
        Student s = session.get(Student.class, student_id);
        if(s == null)
        {
            return "Can not find the student";
        }
        Course c = CourseRepository.getSession().get(Course.class, course_id);
        if(c == null)
        {
            return "Can not find the course";
        }

        Set<Course> courseSet = s.getCourseSet();
        courseSet.add(c);
        s.setCourseSet(courseSet);
        session.beginTransaction();
        session.merge(s);
        session.getTransaction().commit();
        return "Add Course successful";
    }

    public String removeCourse(Long student_id, Long course_id)
    {
        Session session = getSession();
        Student student = session.get(Student.class, student_id);
        Set<Course> courseSet = student.getCourseSet();
        Course tmp = null;
        for(Course c: courseSet)
        {
            if(c.getCourse_id() == course_id)
            {
                tmp = c;
                break;
            }
        }
        if(tmp != null)
        {
            courseSet.remove(tmp);
            student.setCourseSet(courseSet);
            session.beginTransaction();
            session.merge(student);
            session.getTransaction().commit();
            return "remove course successful";
        }
        else
        {
            return "delete course failed\n this course has been deleted.";
        }
    }

    public String deleteStudent(Long student_id)
    {
        Session session  = getSession();;
        session.beginTransaction();
        Student student = session.get(Student.class, student_id);
        if(student != null)
        {
            if(student.getCourseSet().size() > 0)
            {
                return "delete student failed:\nThe student still have some course.";
            }
            else
            {
                session.delete(student);
                session.getTransaction().commit();
                return "delete student successful:\n" + student.toString();
            }
        }
        else
        {
            return "delete student failed:\nthis student has already deleted.";
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
            configuration.addAnnotatedClass(Student.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();
        }
    }
}

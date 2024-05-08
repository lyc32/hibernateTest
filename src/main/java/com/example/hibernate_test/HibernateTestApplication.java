package com.example.hibernate_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateTestApplication
{
    /*
      1.	Basic CRUD Operations: Implement methods to create, read, update, and delete records using Hibernate.

	  2.	One-to-Many Relationship:
	        Create entities with a one-to-many relationship
	        (e.g., a Department entity with multiple Employee entities)
	        and write code to persist and retrieve data maintaining this relationship.
	  3.	Many-to-Many Relationship:
	        Implement entities with a many-to-many relationship
	        (e.g., Student and Course entities)
	        and write code to manage this relationship using Hibernate.
	  4.	Hibernate Criteria API Usage:
	        Write code to retrieve records using Hibernate Criteria API based on specific criteria
	        (e.g., fetching employees with a salary greater than a certain amount).
     */

    public static void main(String[] args) {
        SpringApplication.run(HibernateTestApplication.class, args);
    }

}

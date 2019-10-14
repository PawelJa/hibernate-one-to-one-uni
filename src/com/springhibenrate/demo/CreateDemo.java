package com.springhibenrate.demo;

import com.springhibenrate.entity.Instructor;
import com.springhibenrate.entity.InstructorDetail;
import com.springhibenrate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // create the objects
//            Instructor instructor =
//                    new Instructor("Chad", "Darby", "chad@google.com");
//            InstructorDetail instructorDetail =
//                    new InstructorDetail("http://www.chad.com/youtube", "coding");
            Instructor instructor =
                    new Instructor("Madhu", "Patel", "madhu@google.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("http://www.youtube.com/madhu", "guitar");

            // associate the object
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor object
            //
            // Note: this will also save the details object
            // because of CascadeType.ALL
            System.out.println("Saving instructor... " + instructor);
            session.save(instructor);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}

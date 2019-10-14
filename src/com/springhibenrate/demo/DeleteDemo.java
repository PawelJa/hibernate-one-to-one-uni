package com.springhibenrate.demo;

import com.springhibenrate.entity.Instructor;
import com.springhibenrate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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
            // start a transaction
            session.beginTransaction();

            // get the instructor by primary key: id
            int id = 1;
            Instructor instructor =
                    session.get(Instructor.class, id);

            System.out.println("Founded instructor: " + instructor);
            if (instructor != null) {
                System.out.println("Deleting: " + instructor);

                // Note: will also delete associated "details" object
                // because of CascadeType.All
                //
                session.delete(instructor);
            }

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done !");

        } finally {
            sessionFactory.close();
        }
    }
}

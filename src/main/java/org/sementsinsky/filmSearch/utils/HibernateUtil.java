package org.sementsinsky.filmSearch.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

public class HibernateUtil {
    private static Session session;
    private static SessionFactory sessionFactory;
    public static Session buildSessionAnnotation() {
        try {
            Configuration configuration = new Configuration().configure();

            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();

            return session;
        }
        catch (HibernateException he) {
            System.err.println("Initial SessionFactory creation failed." + he);
            throw  new ExceptionInInitializerError(he);
        }
    }

    public static Session getSession() {
        return session;
    }

    public static void sessionClose() {
        session.close();
        sessionFactory.close();
    }
}

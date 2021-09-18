package com.hillel.homework_4.utils.hibernateUtil;

import com.hillel.homework_4.pojo.CityPojo;
import com.hillel.homework_4.pojo.CountryPojo;
import com.hillel.homework_4.pojo.RegionPojo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(CountryPojo.class)
                    .addAnnotatedClass(RegionPojo.class)
                    .addAnnotatedClass(CityPojo.class)
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Throwable throwable) {
            System.err.println("Failed to create sessionFactory object." + throwable);
            throwable.printStackTrace();
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}

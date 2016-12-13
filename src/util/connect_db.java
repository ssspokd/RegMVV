package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import regmvv.MainForm;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author nikolaev
 */
public class connect_db {

    private static final SessionFactory sessionFactory;
    private static  SessionFactory sf;
    
    static {
        try {
           
            sessionFactory = new AnnotationConfiguration().configure("connect_db.cfg.xml").buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sf;
    }
    
    public static void shutdawn()
    {
        sessionFactory.close();
    }
    
    public static void s()
    {
         DataSource dataSource = null;
                            java.sql.Connection conn;
                            try {
                                conn = dataSource.getConnection();
                            } catch (SQLException ex) {
                                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
        Configuration configuration = new Configuration().configure("connect_db.cfg.xml");
        sf = configuration.buildSessionFactory(
        new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties())
            //here you apply the custom dataSource
            .applySetting(Environment.DATASOURCE, dataSource)
            .build());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author nikolaev
 */
public class NewHibernateUtil {

    private static  SessionFactory sessionFactory = null;
    private static  ServiceRegistry serviceRegistry;

   private static final Logger LOG = Logger.getLogger(NewHibernateUtil.class.getName());

   private static SessionFactory buildSessionFactory() 
    {
        Configuration configuration = new Configuration();
        Properties pr = new Properties();
        pr.setProperty("hibernate.dialect", "org.hibernate.dialect.FirebirdDialect");
        pr.setProperty("hibernate.connection.driver_class", "org.firebirdsql.jdbc.FBDriver");
        pr.setProperty("hibernate.connection.url", "jdbc:firebirdsql:127.0.0.1/3050:ncore-fssp?lc_ctype=WIN1251");
        pr.setProperty("hibernate.connection.username", "sysddba");
        pr.setProperty("hibernate.connection.password", "masterkey");
        pr.setProperty("hibernate.connection.pool_size", "10");
        pr.setProperty("hibernate.show_sql", "true");
        pr.setProperty("hibernate.connection.isolation", "2");
        pr.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory");
        configuration.configure("connect_db.cfg.xml");
       // configuration.setProperties(pr);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() 
    {       
            return sessionFactory = buildSessionFactory();       
    }
    public static void shutdawn()
    {
        sessionFactory.close();
        
    }
}

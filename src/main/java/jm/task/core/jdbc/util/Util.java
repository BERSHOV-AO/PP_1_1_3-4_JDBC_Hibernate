package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Util {

    private static final Logger logger = Logger.getLogger(Util.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            configuration.setProperty("hibernate.show_sql", "true");

            configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error initializing SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    public static Connection getConnectionJDBC() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }
}

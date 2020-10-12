package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String hostName = "localhost";
    private static String dbName = "javamentor";
    private static String userName = "root";
    private static String password = "Caligula97-";
    private static String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false&serverTimezone=UTC";

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", connectionURL)
                .setProperty("hibernate.connection.username", userName)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return configureSessionFactory();
    }
}

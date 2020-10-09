package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String hostName = "localhost";
    private static String dbName = "javamentor";
    private static String userName = "root";
    private static String password = "Caligula97-";

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName, password);
    }
}

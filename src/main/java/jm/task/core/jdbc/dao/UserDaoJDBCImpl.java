package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private Statement statement = null;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS user (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(45) NOT NULL, lastname VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))";
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String DROP_TABLE_SQL = "DROP TABLE IF EXISTS user";
        try {
            connection = Util.getMySQLConnection();
            statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLE_SQL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT_USER_SQL = "INSERT INTO user VALUES (default, ?, ?, ?)";
        try {
            connection = Util.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            ResultSet tableKeys = preparedStatement.getGeneratedKeys();
            tableKeys.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USER_SQL = "DELETE FROM user WHERE id = ?";
        try {
            connection = Util.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String SELECT_ALL_USERS_SQL = "SELECT * FROM user ";
        List<User> userList = new ArrayList<>();
        try {
            connection = Util.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");
                User user = new User(id, name, lastname, age);
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String CLEAN_USERS_SQL = "DELETE FROM user";
        try {
            connection = Util.getMySQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USERS_SQL);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

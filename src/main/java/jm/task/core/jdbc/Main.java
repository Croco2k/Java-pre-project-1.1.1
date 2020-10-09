package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Almat", "Tazhikenov", (byte) 22);
        userService.saveUser("Ivan", "Ivanov", (byte) 32);
        userService.saveUser("Dua", "Lipa", (byte) 18);
        userService.saveUser("Metro", "Booming", (byte) 20);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "One", (byte) 23);
        userService.saveUser("Ivan", "Ivanov", (byte) 31);
        userService.saveUser("David", "Petrov", (byte) 20);
        userService.saveUser("Misha", "Goodman", (byte) 26);

        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.removeUserById(1);

    }
}

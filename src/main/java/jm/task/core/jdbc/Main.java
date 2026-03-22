package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = Util.getConnection()) {

            UserServiceImpl userService = new UserServiceImpl(connection);

            userService.createUsersTable();

            userService.saveUser("Alex", "One", (byte) 23);
            userService.saveUser("Ivan", "Ivanov", (byte) 31);
            userService.saveUser("David", "Petrov", (byte) 20);
            userService.saveUser("Misha", "Goodman", (byte) 26);

            List<User> users = userService.getAllUsers();
            System.out.println("\nСписок всех пользователей в базе:");
            users.forEach(user ->
                    System.out.println(user.getId() + ": " + user.getName() + " " + user.getLastName() + ", " + user.getAge() + " лет")
            );
             userService.removeUserById(1);
             userService.cleanUsersTable();
             userService.dropUsersTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

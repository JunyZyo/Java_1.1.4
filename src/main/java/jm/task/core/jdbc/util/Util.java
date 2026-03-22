package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/firstquest";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("Успешное подключение");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
            e.printStackTrace();
        }
        return connection;
    }

}

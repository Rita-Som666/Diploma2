package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("jdbc:mysql://localhost:3306/app"), "user", "password");
    }

    @SneakyThrows
    public static boolean checkDataExists(String startTime, String nextMinute) throws SQLException {

        String querySQL = "SELECT * FROM payment_entity WHERE created BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = getConn().prepareStatement(querySQL)) {
            // Устанавливаем значения параметров
            preparedStatement.setString(1, startTime);
            preparedStatement.setString(2, nextMinute);

            // Выполняем запрос и проверяем наличие данных
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Возвращает true, если данные найдены // Возвращает true, если данные найдены
            }
        }
    }

    @SneakyThrows
    public static void cleaner() {
        var conn = getConn();

        QUERY_RUNNER.update(conn, "DELETE FROM payment_entity");
        QUERY_RUNNER.update(conn, "DELETE FROM credit_request_entity");
        QUERY_RUNNER.update(conn, "DELETE FROM order_entity");


    }
}


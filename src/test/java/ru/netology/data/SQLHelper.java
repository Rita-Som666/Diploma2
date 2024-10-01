package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/app";
        String user = "user";
        String password = "password";

        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static boolean checkDataExists(String startTime, String nextMinute, String querySQL) throws SQLException {


        try (PreparedStatement preparedStatement = getConn().prepareStatement(querySQL)) {

            preparedStatement.setString(1, startTime);
            preparedStatement.setString(2, nextMinute);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
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


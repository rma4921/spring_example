package com.estsoft.jdbc;

// 1. DB connection
// 2. SQL 실행 -> Delete로 변경
// 3. 결과 출력

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        String sql = "DELETE FROM students WHERE id = 3";

        try(
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
        ) {
            int deleteRow = statement.executeUpdate(sql);
            System.out.println("삭제된 행 수: " + deleteRow);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}

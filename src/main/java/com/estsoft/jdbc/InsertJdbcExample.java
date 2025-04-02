package com.estsoft.jdbc;

import java.sql.*;

public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        try(
            // 1. DB connection
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ) {
            // 2. SQL 실행 -> Insert로 변경
            int addRow = statement.executeUpdate("INSERT INTO students (name, age, address) VALUES ('바보', 27, '경기도 아무시')");
            // 3. 결과 출력
            System.out.println("삽입된 행 수: " + addRow);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

    }
}

package com.estsoft.jdbc;

import java.sql.*;

// 1. DB connection
// 2. SQL 실행 -> Update로 변경
// 3. 결과 출력

public class UpdateJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "0000";

    public static void main(String[] args) {
        String sql = "UPDATE students SET address = '서울특별시' WHERE id = 2";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ) {
            int updateRow = statement.executeUpdate(sql);
            System.out.println("업데이트된 행 수: " + updateRow);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}

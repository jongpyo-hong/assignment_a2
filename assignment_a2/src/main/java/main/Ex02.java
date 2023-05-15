package main;

import java.sql.*;

public class Ex02 { // 강사님 솔루션
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:orcl3";
        String username = "SCOTT";
        String password = "tiger";

        String sql = "DELETE FROM EMP2 WHERE EMPNO=?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, 1L);

            boolean result = pstmt.execute();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

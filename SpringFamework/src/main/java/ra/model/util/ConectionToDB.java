package ra.model.util;

import java.sql.*;

public class ConectionToDB {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/qlstudent";
    private static String USER = "root";
    private static String PASSWORD = "Balam2003@";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

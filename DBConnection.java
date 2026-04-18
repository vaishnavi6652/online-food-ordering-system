import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/food_ordering",
                "root",
                "vaishnavi"
            );
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}

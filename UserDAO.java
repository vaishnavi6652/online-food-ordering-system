import java.sql.*;

public class UserDAO {

    public void register(String name, String email, String password) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password) VALUES(?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            System.out.println("Registered!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

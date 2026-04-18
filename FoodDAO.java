import java.sql.*;

public class FoodDAO {

    public void showMenu() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM menu");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + ". " +
                    rs.getString("name") + " - ₹" +
                    rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public double getPrice(int id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "insert into menu(name,price)values(?,?)"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("price");

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}

import java.sql.*;
import java.util.Map;

public class OrderDAO {

    // ✅ ADVANCED METHOD (Cart System)
    public int placeOrder(int userId, Map<Integer, Integer> items, FoodDAO foodDAO) {

        try {
            Connection con = DBConnection.getConnection();

            double total = 0;
            for (Map.Entry<Integer, Integer> e : items.entrySet()) {
                total += foodDAO.getPrice(e.getKey()) * e.getValue();
            }

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders(user_id,total,status) VALUES(?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );

            ps.setInt(1, userId);
            ps.setDouble(2, total);
            ps.setString(3, "Placed");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) orderId = rs.getInt(1);

            for (Map.Entry<Integer, Integer> e : items.entrySet()) {
                PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO order_items(order_id,food_id,quantity) VALUES(?,?,?)"
                );
                ps2.setInt(1, orderId);
                ps2.setInt(2, e.getKey());
                ps2.setInt(3, e.getValue());
                ps2.executeUpdate();
            }

            System.out.println("Total ₹: " + total);
            return orderId;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    // ✅ SIMPLE METHOD (MenuUI ke liye)
    public void placeOrder(String item) {

        try {
            Connection con = DBConnection.getConnection();

            // 👉 Item ka price DB se fetch karo
            PreparedStatement ps1 = con.prepareStatement(
                "SELECT price FROM menu WHERE name=?"
            );
            ps1.setString(1, item);
            ResultSet rs = ps1.executeQuery();

            double price = 0;
            if (rs.next()) {
                price = rs.getDouble("price");
            }

            // 👉 Order insert karo
            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO orders(user_id, total, status) VALUES (?, ?, ?)"
            );

            ps2.setInt(1, 1);   // default user
            ps2.setDouble(2, price);
            ps2.setString(3, "Placed");

            ps2.executeUpdate();

            System.out.println("Order Placed for " + item + " ₹" + price);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

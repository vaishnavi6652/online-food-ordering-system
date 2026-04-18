import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;

public class MenuUI extends JFrame implements ActionListener {

    JList<String> menuList;
    DefaultListModel<String> model;
    JButton orderBtn;

    public MenuUI() {

        setTitle("Food Menu");

        JLabel l = new JLabel("Right-click to select items:");
        l.setBounds(30,10,250,30);

        model = new DefaultListModel<>();
        menuList = new JList<>(model);

        menuList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // ✅ RIGHT CLICK SELECTION ENABLE
        menuList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = menuList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        menuList.addSelectionInterval(index, index);
                    }
                }
            }
        });

        JScrollPane sp = new JScrollPane(menuList);
        sp.setBounds(50,50,200,120);

        orderBtn = new JButton("Place Order");
        orderBtn.setBounds(80,180,120,30);

        add(l);
        add(sp);
        add(orderBtn);

        orderBtn.addActionListener(this);

        loadMenu();

        setSize(320,270);
        setLayout(null);
        setVisible(true);
    }

    // Load menu
    public void loadMenu() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT name FROM menu");

            while(rs.next()){
                model.addElement(rs.getString("name"));
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }

    // Button click
    public void actionPerformed(ActionEvent e) {

        List<String> selectedItems = menuList.getSelectedValuesList();

        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select at least 1 item!");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            double total = 0;
            StringBuilder bill = new StringBuilder("----- BILL -----\n");

            for (String item : selectedItems) {

                PreparedStatement ps = con.prepareStatement(
                    "SELECT price FROM menu WHERE name=?"
                );
                ps.setString(1, item);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    double price = rs.getDouble("price");
                    total += price;

                    bill.append(item + " = ₹" + price + "\n");
                }
            }

            bill.append("-----------------\n");
            bill.append("Total = ₹" + total);

            // ✅ SHOW BILL FIRST
            JOptionPane.showMessageDialog(this, bill.toString());

            // ✅ SAVE ORDER
            OrderDAO dao = new OrderDAO();
            for (String item : selectedItems) {
                dao.placeOrder(item);
            }

            // ✅ FINAL MESSAGE
            JOptionPane.showMessageDialog(this, "Order Placed Successfully!");

        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginUI extends JFrame implements ActionListener {

    JTextField email;
    JPasswordField pass;
    JButton login, register;

    public LoginUI() {

        setTitle("Login");

        // ✅ Background color
        getContentPane().setBackground(new Color(230, 240, 255)); // light blue

        JLabel l1 = new JLabel("Email:");
        l1.setBounds(50,50,100,30);
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        l1.setForeground(Color.DARK_GRAY);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50,90,100,30);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        l2.setForeground(Color.DARK_GRAY);

        email = new JTextField();
        email.setBounds(150,50,150,30);
        email.setBackground(Color.WHITE);

        pass = new JPasswordField();
        pass.setBounds(150,90,150,30);
        pass.setBackground(Color.WHITE);

        // ✅ Login button color
        login = new JButton("Login");
        login.setBounds(60,140,100,30);
        login.setBackground(new Color(0,153,76)); // green
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);

        // ✅ Register button color
        register = new JButton("Register");
        register.setBounds(170,140,100,30);
        register.setBackground(new Color(0,102,204)); // blue
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);

        add(l1); add(l2); add(email); add(pass);
        add(login); add(register);

        login.addActionListener(this);
        register.addActionListener(this);

        setSize(350,250);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        // LOGIN
        if (e.getSource() == login) {
            try {
                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE email=? AND password=?"
                );

                ps.setString(1, email.getText());
                ps.setString(2, new String(pass.getPassword()));

                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    JOptionPane.showMessageDialog(this,"Login Success");
                    new MenuUI();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,"Invalid Login");
                }

            } catch(Exception ex){
                System.out.println(ex);
            }
        }

        // REGISTER
        if (e.getSource() == register) {
            new RegisterUI();
        }
    }
}

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterUI extends JFrame implements ActionListener {

    JTextField name, email;
    JPasswordField pass;
    JButton register;

    public RegisterUI() {

        setTitle("Register");

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(50,40,100,30);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(50,80,100,30);

        JLabel l3 = new JLabel("Password:");
        l3.setBounds(50,120,100,30);

        name = new JTextField();
        name.setBounds(150,40,150,30);

        email = new JTextField();
        email.setBounds(150,80,150,30);

        pass = new JPasswordField();
        pass.setBounds(150,120,150,30);

        register = new JButton("Register");
        register.setBounds(120,170,100,30);

        add(l1); add(l2); add(l3);
        add(name); add(email); add(pass);
        add(register);

        register.addActionListener(this);

        setSize(350,270);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password) VALUES(?,?,?)"
            );

            ps.setString(1, name.getText());
            ps.setString(2, email.getText());
            ps.setString(3, new String(pass.getPassword()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Registration Successful!");
            dispose(); // close register window

        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}

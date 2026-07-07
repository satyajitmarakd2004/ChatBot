package com.ChatBot;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Register extends JFrame {

    JLabel title, userLabel, passLabel;
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnRegister;

    public Register() {

        setTitle("User Registration");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        title = new JLabel("User Registration");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(90, 20, 250, 30);

        userLabel = new JLabel("Username");
        userLabel.setBounds(50, 80, 100, 25);

        txtUser = new JTextField();
        txtUser.setBounds(150, 80, 180, 25);

        passLabel = new JLabel("Password");
        passLabel.setBounds(50, 120, 100, 25);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 120, 180, 25);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(130, 180, 120, 35);

        add(title);
        add(userLabel);
        add(txtUser);
        add(passLabel);
        add(txtPass);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtUser.getText().trim();
                String password = new String(txtPass.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Username and Password");
                    return;
                }
                System.out.println("Button Clicked");

                try {

                    Connection con = DatabaseConnection.getConnection();

                    if (con == null) {
                        JOptionPane.showMessageDialog(null, "Database Connection Failed!");
                        return;
                    }

                    // Check existing username
                    String checkSql = "SELECT * FROM users WHERE username=?";
                    PreparedStatement checkPs = con.prepareStatement(checkSql);
                    checkPs.setString(1, username);

                    ResultSet rs = checkPs.executeQuery();

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "Username Already Exists!");

                    } else {

                        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

                        PreparedStatement ps = con.prepareStatement(sql);

                        ps.setString(1, username);
                        ps.setString(2, password);

                        int result = ps.executeUpdate();

                        System.out.println("Rows Inserted = " + result);

                        if (result > 0) {

                            JOptionPane.showMessageDialog(null, "Registration Successful!");

                            dispose();
                            new Login();

                        } else {

                            JOptionPane.showMessageDialog(null, "Registration Failed!");

                        }
                    }

                    con.close();

                } catch (Exception ex) {

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }
            }
        });

        setVisible(true);
    }
}
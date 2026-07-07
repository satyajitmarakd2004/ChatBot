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

public class Login extends JFrame {

    JLabel lblTitle, lblUsername, lblPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin, btnRegister;

    public Login() {

        setTitle("User Login");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        lblTitle = new JLabel("User Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(130,20,150,30);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(40,80,100,25);

        txtUsername = new JTextField();
        txtUsername.setBounds(140,80,180,25);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(40,120,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140,120,180,25);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60,190,100,35);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200,190,100,35);

        add(lblTitle);
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnRegister);

        // Login Button
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                try {

                    Connection con = DatabaseConnection.getConnection();

                    String sql = "SELECT * FROM users WHERE username=? AND password=?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, username);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();

                    if(rs.next()) {

                        JOptionPane.showMessageDialog(null, "Login Successful!");

                        dispose();

                        new ChatBot(username);

                    } else {

                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!");

                    }

                    con.close();

                } catch(Exception ex) {

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Error!");

                }

            }
        });

        // Register Button
        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new Register();

            }
        });

        setVisible(true);
    }
}
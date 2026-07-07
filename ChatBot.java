package com.ChatBot;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatBot extends JFrame {

    JTextArea chatArea;
    JTextField messageField;
    JButton sendButton, clearButton, logoutButton;
    String username;

    public ChatBot(String username) {

        this.username = username;

        setTitle("AI ChatBot - Welcome " + username);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();

        sendButton = new JButton("Send");
        clearButton = new JButton("Clear");
        logoutButton = new JButton("Logout");

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(logoutButton, BorderLayout.WEST);
        panel.add(messageField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();

        rightPanel.add(clearButton);
        rightPanel.add(sendButton);

        panel.add(rightPanel, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        chatArea.append("Bot : Hello " + username + "!\n");
        chatArea.append("Bot : Welcome to AI ChatBot.\n");
        chatArea.append("Bot : Ask me anything.\n\n");

        // SEND BUTTON
        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String msg = messageField.getText().trim();

                if(msg.isEmpty()) {
                    return;
                }

                chatArea.append(username + " : " + msg + "\n");

                String reply = ChatService.getReply(msg);

                // Save Chat in Database
                ChatHistory.saveChat(username, msg, reply);

                chatArea.append("Bot : " + reply + "\n\n");

                messageField.setText("");

            }
        });

        // CLEAR BUTTON
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                chatArea.setText("");

            }
        });

        // LOGOUT BUTTON
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to Logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION){

                    dispose();
                    new Login();

                }

            }
        });

        setVisible(true);
    }
}
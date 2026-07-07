package com.ChatBot;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChatHistory {

    public static void saveChat(String username, String question, String answer) {

        try {

            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO chat_history(username,question,answer) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, question);
            ps.setString(3, answer);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
package com.ChatBot;

public class ChatService {

    public static String getReply(String message) {

        message = message.toLowerCase();

        if (message.contains("hi") || message.contains("hello")) {
            return "Hello! How can I help you?";
        }

        if (message.contains("name")) {
            return "My name is AI ChatBot.";
        }

        if (message.contains("java")) {
            return "Java is an Object-Oriented Programming Language.";
        }

        if (message.contains("mysql")) {
            return "MySQL is a Relational Database.";
        }

        if (message.contains("bye")) {
            return "Good Bye! Have a Nice Day.";
        }

        return "Sorry! I don't understand your question.";
    }
}
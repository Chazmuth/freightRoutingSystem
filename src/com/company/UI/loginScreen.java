package com.company.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginScreen {
    private JTextField usernameEntry;
    private JPanel panel1;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;


    public static void main(String[] args) {
        JFrame frame = new JFrame("loginScreen");
        frame.setContentPane(new loginScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public loginScreen() {
        loginButton.addActionListener(e -> {
            if (usernameEntry.getText().equals("username") && (new String(passwordField.getPassword())).equals("password")
            ) {
                System.out.println("login successful");
            }else{
                System.out.println("username or password incorrect");
            }
        });

        //moodle calculator project for code to link to another GUI

    }

}

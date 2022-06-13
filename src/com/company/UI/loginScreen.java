package com.company.UI;

import javax.swing.*;

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

    public loginScreen(){

    }

}

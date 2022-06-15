package com.company.UI;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class loginScreen {
    private JTextField usernameEntry;
    private JPanel panel1;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel loginSuccessful;

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginScreen");
        frame.setContentPane(new loginScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.pack();
        frame.setSize(250, 250);
        frame.setVisible(true);
    }

    public loginScreen() {
        loginButton.addActionListener(e -> {
            if (usernameEntry.getText().equals("username") && (new String(passwordField.getPassword())).equals("password")
            ) {
                loginSuccessful.setText("login successful");
                // find out how to close a window in this way of creating windows
            }else{
                try {
                    loginSuccessful.setText("username or password incorrect");

                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //moodle calculator project for code to link to another GUI

    }

}

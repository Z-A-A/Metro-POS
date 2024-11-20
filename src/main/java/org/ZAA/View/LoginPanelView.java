package org.ZAA.View;

import javax.swing.*;
import java.awt.*;

public class LoginPanelView extends JPanel {
    public LoginPanelView(LoginRegisterPanel parentPanel) {
        setLayout(new BorderLayout());

        // Left side: Welcome message
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.blue); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 0));

        JLabel welcomeLabel = new JLabel("Welcome to METRO POS!", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel("<html>Enter your personal details<br>to start working</html>", SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signupButton = new JButton("SIGN UP");
        signupButton.setBackground(Color.WHITE);
        signupButton.setForeground(Color.blue);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Switch to register panel when clicked
        signupButton.addActionListener(e -> parentPanel.switchToPanel("register"));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(welcomeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(infoLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(signupButton);
        leftPanel.add(Box.createVerticalGlue());

        // Right side: Login form
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel loginLabel = new JLabel("Sign In", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 28));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = new JButton("SIGN IN");
        loginButton.setBackground(Color.blue);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel forgotLabel = new JLabel("Forgot your password?");
        forgotLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotLabel.setForeground(Color.blue);
        forgotLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(loginLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(emailField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(loginButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(forgotLabel);
        rightPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}

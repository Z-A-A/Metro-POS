package org.ZAA.View;

import javax.swing.*;
import java.awt.*;

public class RegisterPanelView extends JPanel {
    public RegisterPanelView(LoginRegisterPanel parentPanel) {
        setLayout(new BorderLayout());

        // Left side: Welcome message
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.blue); 
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 0));

        JLabel welcomeLabel = new JLabel("Welcome Back!", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel("<html>To keep connected with us,<br>please login with your personal info</html>", SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signinButton = new JButton("SIGN IN");
        signinButton.setBackground(Color.WHITE);
        signinButton.setForeground(Color.blue);
        signinButton.setFont(new Font("Arial", Font.BOLD, 16));
        signinButton.setFocusPainted(false);
        signinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Switch to login panel when clicked
        signinButton.addActionListener(e -> parentPanel.switchToPanel("login"));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(welcomeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(infoLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(signinButton);
        leftPanel.add(Box.createVerticalGlue());

        // Right side: Register form
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel registerLabel = new JLabel("Create Account", SwingConstants.CENTER);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setMaximumSize(new Dimension(300, 40));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton registerButton = new JButton("SIGN UP");
        registerButton.setBackground(Color.blue);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(registerLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(emailField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(registerButton);
        rightPanel.add(Box.createVerticalGlue());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}

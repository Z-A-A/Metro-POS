package org.ZAA.View;

import java.awt.CardLayout;

import javax.swing.*;

public class LoginRegisterView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login and Register");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setContentPane(new LoginRegisterPanel());
            frame.setVisible(true);
        });
    }
}

class LoginRegisterPanel extends JPanel {
    private final CardLayout cardLayout;

    public LoginRegisterPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Add the LoginPanel and RegisterPanel
        add(new LoginPanelView(this), "login");
        add(new RegisterPanelView(this), "register");

        // Show the login panel by default
        cardLayout.show(this, "login");
    }

    // Method to switch between panels
    public void switchToPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
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

        JPanel loginPanel = createLoginPanel();
        JPanel registerPanel = createRegisterPanel();

        add(loginPanel, "login");
        add(registerPanel, "register");

        cardLayout.show(this, "login"); // Default view
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Left side: Welcome message
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(34, 177, 76)); // Green
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 0));

        JLabel welcomeLabel = new JLabel("Hello, Friend!", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel infoLabel = new JLabel("<html>Enter your personal details<br>and start your journey with us</html>", SwingConstants.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signupButton = new JButton("SIGN UP");
        signupButton.setBackground(Color.WHITE);
        signupButton.setForeground(new Color(34, 177, 76));
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupButton.addActionListener(e -> cardLayout.show(LoginRegisterPanel.this, "register"));

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
        loginButton.setBackground(new Color(34, 177, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel forgotLabel = new JLabel("Forgot your password?");
        forgotLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotLabel.setForeground(new Color(34, 177, 76));
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

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Left side: Welcome message
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(34, 177, 76)); // Green
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
        signinButton.setForeground(new Color(34, 177, 76));
        signinButton.setFont(new Font("Arial", Font.BOLD, 16));
        signinButton.setFocusPainted(false);
        signinButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signinButton.addActionListener(e -> cardLayout.show(LoginRegisterPanel.this, "login"));

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
        registerButton.setBackground(new Color(34, 177, 76));
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

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        return panel;
    }
}

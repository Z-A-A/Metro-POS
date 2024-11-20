import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create UI components
        JLabel loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(registerButton);

        // Add action listeners
        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterForm();
        });

        // Add components to the frame
        add(loginLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        // Set frame size and make it visible
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(null, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

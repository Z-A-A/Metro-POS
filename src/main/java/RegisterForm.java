import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterForm() {
        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel registerLabel = new JLabel("Register", SwingConstants.CENTER);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(registerButton);
        formPanel.add(backButton);

        // Add action listeners
        registerButton.addActionListener(new RegisterActionListener());
        backButton.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        add(registerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class RegisterActionListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                dispose();
                new LoginForm();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

package org.ZAA.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataEntryOperator extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JButton addButton;
    private int buttonCount = 0;

    public DataEntryOperator() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create left panel with scrollable buttons
        leftPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(buttonPanel);
        addButton = new JButton("Add");
        styleButton(addButton);
        addButton.addActionListener(new AddButtonActionListener());

        leftPanel.add(scrollPane, BorderLayout.CENTER);
        leftPanel.add(addButton, BorderLayout.SOUTH);

        // Create right panel to display information
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(255, 253, 208)); // Light cream background
        JLabel infoLabel = new JLabel("Information will be displayed here", SwingConstants.CENTER);
        rightPanel.add(infoLabel, BorderLayout.CENTER);

        // Add panels to the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(250); // Set fixed width for left panel
        splitPane.setResizeWeight(0.0); // Fixed width for left panel
        add(splitPane, BorderLayout.CENTER);

        // Set frame size and make it visible
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 50)); // Set fixed button size
        button.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Increase spacing
        ));
    }

    private class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonCount++;
            JButton newButton = new JButton("Button " + buttonCount);
            styleButton(newButton);
            newButton.addActionListener(new ButtonActionListener());
            buttonPanel.add(newButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            JLabel infoLabel = new JLabel("You clicked: " + buttonText, SwingConstants.CENTER);
            rightPanel.removeAll();
            rightPanel.add(infoLabel, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataEntryOperator::new);
    }
}
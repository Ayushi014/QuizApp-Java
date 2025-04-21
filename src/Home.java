import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame {
    private String currentUsername;

    public Home(String username) {
        this.currentUsername = username;

        setTitle("Quiz App - Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setBackground(Color.CYAN);

        JLabel welcomeLabel = new JLabel("Welcome " + currentUsername, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        homePanel.add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(new Color(100, 149, 237)); // Dodger Blue
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> startQuiz());
        homePanel.add(startButton, BorderLayout.SOUTH);

        add(homePanel);

        setVisible(true);
    }

    private void startQuiz() {
        dispose();
        new Quiz(currentUsername); // Start quiz after home screen
    }

    public static void main(String[] args) {
        new Home("admin"); // Start the home screen with username "admin"
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Quiz extends JFrame {
    private String currentUsername;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private ArrayList<Question> questions;
    private JPanel mainPanel; // Panel to hold current question content

    public Quiz(String username) {
        this.currentUsername = username;
        this.questions = loadQuestions();

        setTitle("Quiz App - Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set custom gradient background
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        setContentPane(gradientPanel);

        displayQuestion(); // Start quiz
        setVisible(true);
    }

    private void displayQuestion() {
        // Remove old question panel if exists
        if (mainPanel != null) {
            remove(mainPanel);
        }

        if (currentQuestionIndex >= questions.size()) {
            showResults();
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        mainPanel.setOpaque(false); // Transparent background

        JLabel questionLabel = new JLabel(currentQuestion.getQuestionText(), SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(Color.WHITE);
        mainPanel.add(questionLabel);

        ButtonGroup optionsGroup = new ButtonGroup();

        for (String option : currentQuestion.getOptions()) {
            JRadioButton optionButton = new JRadioButton(option);
            optionButton.setFont(new Font("Arial", Font.PLAIN, 16));
            optionButton.setForeground(Color.WHITE);
            optionButton.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent
            optionsGroup.add(optionButton);
            mainPanel.add(optionButton);
        }

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(100, 149, 237));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> checkAnswer(optionsGroup));
        mainPanel.add(nextButton);

        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void checkAnswer(ButtonGroup optionsGroup) {
        String selectedOption = "";
        Enumeration<AbstractButton> elements = optionsGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            if (button.isSelected()) {
                selectedOption = button.getText();
                break;
            }
        }

        if (selectedOption.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion(); // Load next question
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score: " + score);
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Quiz Completed", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            currentQuestionIndex = 0;
            score = 0;
            displayQuestion(); // Restart quiz
        } else {
            dispose();
            new Home(currentUsername); // Go back to home screen
        }
    }

    private ArrayList<Question> loadQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of India?", new String[]{"Delhi", "Mumbai", "Kolkata", "Bangalore"}, "Delhi"));
        questions.add(new Question("Which programming language is this quiz built in?", new String[]{"Java", "Python", "C", "JavaScript"}, "Java"));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4"));
        return questions;
    }

    public static void main(String[] args) {
        new Quiz("admin");
    }
}

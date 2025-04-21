import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private ArrayList<User> users;

    public Register(ArrayList<User> usersList) {
        this.users = usersList;

        setTitle("Quiz App - Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmField = new JPasswordField();

        JButton registerBtn = new JButton("Register");

        registerBtn.addActionListener(e -> registerUser());

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(confirmLabel);
        panel.add(confirmField);
        panel.add(new JLabel());
        panel.add(registerBtn);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirm = String.valueOf(confirmField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return;
            }
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        users.add(new User(username, password));
        JOptionPane.showMessageDialog(this, "Registered Successfully!");
        dispose();
        new Login(); // Go back to login
    }
}

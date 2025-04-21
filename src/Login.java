import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ✅ Use your already created GradientPanel
        GradientPanel panel = new GradientPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Adds padding

        // Username Label & Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.WHITE);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        // Password Label & Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // For now, just allow any login
                if (!username.isEmpty() && !password.isEmpty()) {
                    new Home(username); // Move to Home class
                    dispose(); // Close Login
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter username and password");
                }
            }
        });
        panel.add(new JLabel()); // empty cell
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login(); // Start login
    }
}

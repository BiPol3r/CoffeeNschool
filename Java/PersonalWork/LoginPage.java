import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JButton loginButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPanel mainPanel, centerPanel;

    public LoginPage() {
        mainFrame();
        Panels();
        initializeComponents();
        addToPanel();
        addEventHandler();

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void mainFrame() {
        setTitle("Login - Welcome Back");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void Panels() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(new Color(2, 4, 54));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void initializeComponents() {
        loginButton = new JButton("Login");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
    }

    private void addToPanel() {
        // Title
        JLabel titleLabel = new JLabel("Login to Your Account");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createHorizontalStrut(800)); // Keeping EXACT as yours

        // Email
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setForeground(Color.WHITE);
        centerPanel.add(emailLabel);
        centerPanel.add(emailField);

        // Password
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Color.WHITE);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        // Login Button
        centerPanel.add(Box.createHorizontalStrut(400)); // Keeping EXACT
        centerPanel.add(loginButton);

        // Sign Up Link
        JLabel signUpLink = new JLabel("<html><u>Don't have an account? Sign up here</u></html>");
        signUpLink.setForeground(Color.GREEN);
        signUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new SignUpPage();
            }
        });
        centerPanel.add(signUpLink);
    }

    private void addEventHandler() {
        loginButton.addActionListener(e -> {
            if (emailField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter both email and password!",
                        "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String email = emailField.getText().trim().toLowerCase();
            String password = new String(passwordField.getPassword());

            try {
                boolean success = DBHelper.loginUser(email, password);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Login successful!\nWelcome back!");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });
    }
}

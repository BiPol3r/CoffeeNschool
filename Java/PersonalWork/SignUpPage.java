import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SignUpPage extends JFrame {
    private JButton signUpButton;
    private JTextField emailField, fullNameField, phoneNumberField;
    private JPasswordField passwordField;
    private JComboBox<String> countryBox;
    private JPanel mainPanel, centerPanel;

    public SignUpPage() {
        mainFrame();
        mainPanel();
        initializeComponents();
        addToPanel();
        addEventHandler();

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void mainFrame() {
        setTitle("Sign Up - Create Account");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void mainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        centerPanel.setBackground(new Color(2, 4, 54));

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void initializeComponents() {
        signUpButton = new JButton("Sign Up");
        emailField = new JTextField(20);
        fullNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        passwordField = new JPasswordField(20);
        countryBox = new JComboBox<>(new String[]{"KENYA", "USA", "UK", "Canada", "Australia"});
    }

    private void addToPanel() {
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createHorizontalStrut(400)); 

        JLabel nameLabel = new JLabel("Full Name: ");
        nameLabel.setForeground(Color.WHITE);
        centerPanel.add(nameLabel);
        centerPanel.add(fullNameField);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setForeground(Color.WHITE);
        centerPanel.add(emailLabel);
        centerPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneLabel.setForeground(Color.WHITE);
        centerPanel.add(phoneLabel);
        centerPanel.add(phoneNumberField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Color.WHITE);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        JLabel countryLabel = new JLabel("Country: ");
        countryLabel.setForeground(Color.WHITE);
        centerPanel.add(countryLabel);
        centerPanel.add(countryBox);

        centerPanel.add(Box.createHorizontalStrut(400));
        centerPanel.add(signUpButton);

        JLabel loginLink = new JLabel("<html><u>Already have an account? Login here</u></html>");
        loginLink.setForeground(Color.CYAN);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new LoginPage();
            }
        });
        centerPanel.add(loginLink);
    }

    private void addEventHandler() {
        signUpButton.addActionListener(e -> {
            if (emailField.getText().isEmpty() ||
                fullNameField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty() ||
                passwordField.getPassword().length == 0) {

                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            String email = emailField.getText().trim().toLowerCase();
            String fullName = fullNameField.getText().trim();
            String phone = phoneNumberField.getText().trim();
            String password = new String(passwordField.getPassword());
            String country = (String) countryBox.getSelectedItem();

            try {
                if (DBHelper.emailExists(email)) {
                    JOptionPane.showMessageDialog(this, "Email already exists!");
                    return;
                }

                boolean inserted = DBHelper.registerUser(fullName, email, phone, password, country);

                if (inserted) {
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                    dispose();
                    new LoginPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });
    }
}

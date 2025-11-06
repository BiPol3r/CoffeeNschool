import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;t

public class LoginPage extends JFrame{
    private JButton button;
    private JTextField emailField, fullNameField, phoneNumberfield;
    private JPasswordField passwordField;
    private JComboBox countryBox;
    private JPanel mainPanel, centerPanel;

    public LoginPage(){
        mainFrame();
        mainPanel();
        initializeComponents();
        addEventHandler();
        addToPanel();
        add(mainPanel);

        setVisible(true);
    }

        private void mainFrame(){
            setTitle("Zuko's Log In Page");
            setSize(450, 450);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);
            
        }
        private void mainPanel(){
            mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            centerPanel = new JPanel();
            centerPanel.setLayout(new FlowLayout(20));
            centerPanel.setBackground(new Color(2,4,54));

            mainPanel.add(centerPanel, BorderLayout.CENTER);
            
            
        }

        private void initializeComponents(){
            button = new JButton("Login");
            emailField = new JTextField(20);
            fullNameField = new JTextField(20);
            phoneNumberfield = new JTextField(20);
            passwordField = new JPasswordField(20);
            countryBox = new JComboBox( new String[]{"USA", "UK", "Canada", "Australia"});

        }

        private void addToPanel(){
            JLabel nameLable = new JLabel("Full Name: ");
            nameLable.setForeground(Color.WHITE);
            centerPanel.add(nameLable);
            centerPanel.add(fullNameField);

            JLabel emailLabel = new JLabel("Email: ");
            emailLabel.setForeground(Color.WHITE);
            centerPanel.add(emailLabel);
            centerPanel.add(emailField);
            centerPanel.add(Box.createHorizontalStrut(10));

            JLabel phoneLabel = new JLabel("Phone Number: ");
            phoneLabel.setForeground(Color.WHITE);
            centerPanel.add(phoneLabel);
            centerPanel.add(phoneNumberfield);
            centerPanel.add(Box.createHorizontalStrut(10));

            JLabel passwordLabel = new JLabel("Password: ");
            passwordLabel.setForeground(Color.WHITE);
            centerPanel.add(passwordLabel);
            centerPanel.add(passwordField);
            centerPanel.add(Box.createHorizontalStrut(10));


            JLabel countryLabel = new JLabel("Country: ");
            countryLabel.setForeground(Color.WHITE);
            centerPanel.add(countryLabel);
            centerPanel.add(countryBox);
            centerPanel.add(Box.createHorizontalStrut(10));

            centerPanel.add(button);

        }
       private void addEventHandler() {
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Check empty fields
            if (emailField.getText().isEmpty() ||
                fullNameField.getText().isEmpty() ||
                phoneNumberfield.getText().isEmpty() ||
                passwordField.getPassword().length == 0) {

                JOptionPane.showMessageDialog(mainPanel, "All fields are required!");
                return;
            }

            // Retrieve user input
            String email = emailField.getText();
            String fullName = fullNameField.getText();
            String phoneNumber = phoneNumberfield.getText();
            String password = new String(passwordField.getPassword());
            String country = (String) countryBox.getSelectedItem();

            // For now just show success
            JOptionPane.showMessageDialog(mainPanel,
                    "Login Successful!\n" +
                    "Email: " + email + "\n" +
                    "Full Name: " + fullName + "\n" +
                    "Phone: " + phoneNumber + "\n" +
                    "Country: " + country);

            System.exit(0);
        }
    });
}


    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new LoginPage());
    }

}
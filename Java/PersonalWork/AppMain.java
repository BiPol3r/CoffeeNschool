import javax.swing.*;
import java.sql.SQLException;

public class AppMain {
    public static void main(String[] args) {
        // Load driver (optional with modern drivers but okay to keep)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {}

        // Initialize DB once
        try {
            DBHelper.initDatabase();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "DB initialization failed: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}

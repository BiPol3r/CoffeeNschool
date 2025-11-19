import java.sql.*;

public class DBHelper {
    private static final String DB_NAME = "zuko_db";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String URL_NO_DB = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";    // change if needed
    private static final String PASS = "metro";   // change if needed

    public static void initDatabase() throws SQLException {
        createDatabaseIfNotExists();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // Create table if not exists - do NOT drop existing table
            String create = "CREATE TABLE IF NOT EXISTS users (" 
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "full_name VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL UNIQUE,"
                + "phone VARCHAR(64),"
                + "password VARCHAR(255) NOT NULL,"
                + "country VARCHAR(100),"
                + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ");";
            stmt.execute(create);
        }
    }

    private static void createDatabaseIfNotExists() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL_NO_DB, USER, PASS);
             Statement stmt = conn.createStatement()) {
            String createDB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.execute(createDB);
        }
    }

    public static boolean registerUser(String fullName, String email, String phone,
                                       String password, String country) throws SQLException {
        String insertSql = "INSERT INTO users(full_name, email, phone, password, country) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement p = conn.prepareStatement(insertSql)) {
            p.setString(1, fullName);
            p.setString(2, email);
            p.setString(3, phone);
            p.setString(4, password); // plaintext as requested
            p.setString(5, country);
            p.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            // duplicate email
            return false;
        }
    }

    public static boolean loginUser(String email, String password) throws SQLException {
        String sql = "SELECT password FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, email);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(password);
                } else {
                    return false;
                }
            }
        }
    }

    public static boolean emailExists(String email) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, email);
            try (ResultSet rs = p.executeQuery()) {
                return rs.next();
            }
        }
    }
}

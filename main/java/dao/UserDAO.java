package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String DB_URL = System.getenv("DB_URL") != null ?
        System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/userdb";
    private static final String DB_USER = System.getenv("DB_USER") != null ?
        System.getenv("DB_USER") : "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD") != null ?
        System.getenv("DB_PASSWORD") : "Yash@1503";

    // Method to register a new user
    public boolean registerUser(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Store password securely in real applications
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate user login
    public boolean validateUser(String email, String password) throws Exception {
    	String query = "SELECT * FROM users WHERE email=? AND password=BINARY ?";
    	try (Connection conn = getConnection();
    	     PreparedStatement stmt = conn.prepareStatement(query)) {
    	    
    	    stmt.setString(1, email);
    	    stmt.setString(2, password);
    	    
    	    System.out.println("Query: " + query);
    	    System.out.println("Email: " + email);
    	    System.out.println("Password: " + password);

    	    
    	    ResultSet rs = stmt.executeQuery();
    	    if (rs.next()) {
    	        return true; // User exists
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	return false;

    }

    // Database connection method
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

package org.keyin.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.keyin.database.DatabaseConnection;

public class UserDAO {

  public void addUser(User user) throws SQLException {
    String query = "INSERT INTO users (username, password, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getPhoneNumber());
      preparedStatement.setString(5, user.getAddress());
      preparedStatement.setString(6, user.getRole());

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error adding user to database: " + e.getMessage());
      throw e;
    }
  }

  public boolean deleteUser(int userId) throws SQLException {
    String query = "DELETE FROM users WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      return stmt.executeUpdate() > 0;
    }
  }

  public User getUserByUsername(String username) throws SQLException {
    String sql = "SELECT * FROM users WHERE username = ?";
    DriverManager DatabaseConnector;
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, username);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new User(
              rs.getInt("id"),
              rs.getString("username"),
              rs.getString("password"),
              rs.getString("role")

          );
        }
      }
    }

    return null;
  }

  public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new User(
              rs.getInt("id"),
              rs.getString("username"),
              rs.getString("password"),
              rs.getString("email"),
              rs.getString("phone"),
              rs.getString("address"),
              rs.getString("role"));
        }
      }
    }
    return null;
  }

  public List<User> getAllUsers() throws SQLException {
    List<User> userList = new ArrayList<>();

    String query = "SELECT * FROM users";
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        User user = new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getString("role"));
        userList.add(user);
      }
    }

    return userList;
  }

}

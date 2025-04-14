package org.keyin.user;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDao;

    public UserService() {
        userDao = new UserDAO();
    }

    public void addUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDao.addUser(user);
    }

    public boolean deleteUser(int userId) throws SQLException {
        return userDao.deleteUser(userId);
    }

    public User loginForUser(String username, String enteredPassword) throws SQLException {
        User userFromDb = userDao.getUserByUsername(username);

        if (userFromDb != null && BCrypt.checkpw(enteredPassword, userFromDb.getPassword())) {
            return userFromDb;
        }

        return null;
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    // public void loadDefaultUsers() {
    // try {
    // User adminUser = new User(1, "admin", "adminpass", "admin@site.com",
    // "123-4567", "Admin HQ", "admin");
    // User memberUser = new User(2, "member", "memberpass", "member@site.com",
    // "987-6543", "Member Lane", "member");
    // User trainerUser = new User(3, "trainer", "trainerpass", "trainer@site.com",
    // "555-9999", "Trainer Gym", "trainer");

    // addUser(adminUser);
    // addUser(memberUser);
    // addUser(trainerUser);

    // } catch (SQLException e) {
    // System.out.println("Error loading default users: " + e.getMessage());
    // }
    // }

}

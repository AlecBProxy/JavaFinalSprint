package org.keyin.user;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void addUser(User user) throws SQLException {
        userDao.addUser(user); 
    }

    public User loginForUser(String username, String password) throws SQLException {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }
}

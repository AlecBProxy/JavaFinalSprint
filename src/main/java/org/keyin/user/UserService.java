package org.keyin.user;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void addUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDao.addUser(user); 
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
}

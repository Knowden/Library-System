package main;

import base_data.User;
import dao.user_dao.UserDAOImpl;

public class UserCenter {

    private UserDAOImpl impl = new UserDAOImpl();

    public void addUser(User user) {
        impl.addUser(user);
    }

    public void deleteUser(int userId) {
        impl.deleteUser(userId);
    }

    public User getUser(int id) {
        return impl.getUserById(id);
    }

    public User getUser(String name) {
        return impl.getUserByName(name);
    }
}

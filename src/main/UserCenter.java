package main;

import base_data.User;
import dao.user_dao.UserDAOImpl;

public class UserCenter {

    public void addUser(User user) {
        UserDAOImpl impl = new UserDAOImpl();
        impl.addUser(user);
    }

    public void deleteUser(User user) {
        UserDAOImpl impl = new UserDAOImpl();
        impl.deleteUser(user);
    }

    public User getUser(int id) {
        UserDAOImpl impl = new UserDAOImpl();
        return impl.getUserById(id);
    }

    public User getUser(String name) {
        UserDAOImpl impl = new UserDAOImpl();
        return impl.getUserByName(name);
    }
}

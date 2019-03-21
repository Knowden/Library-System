package dao.user_dao;

import main.User;

interface UserDAO {

    public User getUserByName(String name);

    public User getUserById(int id);

    public void addUser(User user);

    public void deleteUser(User user);

}

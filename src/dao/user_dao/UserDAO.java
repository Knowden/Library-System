package dao.user_dao;

import base_data.User;

interface UserDAO {

    public User getUserByName(String name);

    public User getUserById(int id);

    public void addUser(User user);

    public void deleteUser(int userId);

    public boolean nameExist(String name);

    public boolean idExist(int userId);
}

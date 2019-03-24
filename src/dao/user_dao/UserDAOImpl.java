package dao.user_dao;

import dao.BaseDao;
import base_data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends BaseDao implements UserDAO {

    @Override
    public User getUserByName(String name) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM User WHERE user_name = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, name);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int userId = rst.getInt("user_id");
                String userPwd = rst.getString("user_pwd");
                closeAll(connect, check, rst);
                return new User(name, userPwd, userId);
            }
            else {
                closeAll(connect, check, rst);
                throw new IllegalArgumentException("User Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM User WHERE user_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, id);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                String userName = rst.getString("user_name");
                String userPwd = rst.getString("user_pwd");
                closeAll(connect, check, rst);
                return new User(userName, userPwd, id);
            }
            else {
                closeAll(connect, check, rst);
                throw new IllegalArgumentException("User Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) throws IllegalArgumentException {
        try {
            String addSql = "INSERT INTO User VALUES (?, ?, ?)";
            executeSQL(addSql, user.getUserId(), user.getName(), user.getPassWord());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            String delSql = "DELETE FROM User WHERE user_id = ?";
            executeSQL(delSql, userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

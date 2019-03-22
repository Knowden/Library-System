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
                return new User(name, userPwd, userId);
            }
            else {
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
                return new User(userName, userPwd, id);
            }
            else {
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
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM User WHERE user_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, user.getUserId());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                throw new IllegalArgumentException("User Already Exist!");
            }
            String addSql = "INSERT INTO User VALUES (?, ?)";
            Object[] param = new Object[2];
            param[0] = user.getUserId();
            param[1] = user.getName();
            executeSQL(addSql, param);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            int id = user.getUserId();
            String delSql = "DELETE FROM User WHERE user_id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            executeSQL(delSql, param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

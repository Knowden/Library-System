package dao;

import java.sql.*;

/**
 * 21-oo
 * Created on:      2019-03-20 16:28
 * Original author: Nocturne
 */
public class BaseDao {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/LibraryServer?useSSL=false";
    private static final String user = "root";
    private static final String password = "hanwenzhao";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeAll(Connection connect, Statement state, ResultSet result) throws SQLException {
        closeAll(connect, state);
        if (result != null) {
            result.close();
        }
    }

    public static void closeAll(Connection connect, Statement state) throws SQLException {
        if (connect != null) {
            connect.close();
        }
        if (state != null) {
            state.close();
        }
    }

    public int executeSQL(String preparedSql, Object... param) throws ClassNotFoundException {
        Connection connect = null;
        PreparedStatement preState = null;
        ResultSet result = null;

        try {
            connect = getConnection();
            preState = connect.prepareStatement(preparedSql);

            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    preState.setObject(i + 1, param[i]);
                }
            }

            preState.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(connect, preState, result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

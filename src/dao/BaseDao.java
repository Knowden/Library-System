package dao;

import java.sql.*;

/**
 * 21-oo
 * Created on:      2019-03-20 16:28
 * Original author: Nocturne
 */
public class BaseDao {

    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://127.0.0.1:3306/LibraryServer?useSSL=false";
    private final static String user = "root";
    private final static String password = "hanwenzhao";

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
        if (connect != null) {
            connect.close();
        }
        if (state != null) {
            state.close();
        }
        if (result != null) {
            result.close();
        }
    }

    public int executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
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

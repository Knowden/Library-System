package dao.record_dao;

import dao.BaseDao;
import base_data.Date;
import base_data.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-21 16:31
 * Original author: Nocturne
 */
public class RecordDAOImpl extends BaseDao implements RecordDAO {


    @Override
    public void addRecord(Record record) {
        try {
            String addSql = "INSERT INTO Record VALUES (null, ?, ?, ?, ?)";
            Object[] param = new Object[4];
            param[0] = record.getUserId();
            param[1] = record.getBookId();
            param[2] = record.getBorrowDate().toString();
            param[3] = record.getReturnDate().toString();
            executeSQL(addSql, param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(Record record) {
        try {
            String delSql = "DELETE FROM Record WHERE user_id = ? AND book_id = ? ";
            Object[] param = new Object[2];
            param[0] = record.getUserId();
            param[1] = record.getBookId();
            executeSQL(delSql, param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Record> checkOneRecords(int userId) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Record WHERE user_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, userId);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                ArrayList<Record> records = new ArrayList<>();
                int bookId = rst.getInt("book_id");
                Date borrowDay = new Date(rst.getDate("borrow_day").toString());
                Date returnDay = new Date(rst.getDate("return_day").toString());
                records.add(new Record(userId, bookId, borrowDay, returnDay));
                while (rst.next()) {
                    bookId = rst.getInt("book_id");
                    borrowDay = new Date(rst.getDate("borrow_day").toString());
                    returnDay = new Date(rst.getDate("return_day").toString());
                    records.add(new Record(userId, bookId, borrowDay, returnDay));
                }
                return records;
            }
            else {
                throw new IllegalArgumentException("No Borrow Record!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

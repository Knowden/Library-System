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
    public void addRecord(Record record) throws IllegalArgumentException{
        try {
            if (!haveRecord(record.getUserId(), record.getBookId())) {
                String addSql = "INSERT INTO Record VALUES (null, ?, ?, ?, ?)";
                executeSQL(addSql, record.getUserId(), record.getBookId(), record.getBorrowDate().toString(), record.getReturnDate().toString());
            }
            else {
                throw new IllegalArgumentException("You have Borrowed This Book!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(int userId, int bookId) throws IllegalArgumentException {
        try {
            if (haveRecord(userId, bookId)) {
                String delSql = "DELETE FROM Record WHERE user_id = ? AND book_id = ?";
                executeSQL(delSql, userId, bookId);
            }
            else {
                throw new IllegalArgumentException("You Have Not Borrow This Book!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean haveRecord(int userId, int bookId) {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Record WHERE user_id = ? AND book_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, userId);
            check.setObject(2, bookId);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                closeAll(connect, check, rst);
                return true;
            }
            closeAll(connect, check, rst);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                records.add(buildRecord(rst));
                while (rst.next()) {
                    records.add(buildRecord(rst));
                }
                closeAll(connect, check, rst);
                return records;
            }
            else {
                closeAll(connect, check, rst);
                throw new IllegalArgumentException("No Borrow Record!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Record buildRecord(ResultSet rst) {
        try {
            int userId = rst.getInt("user_id");
            int bookId = rst.getInt("book_id");
            Date borrowDay = new Date(rst.getDate("borrow_day").toString());
            Date returnDay = new Date(rst.getDate("return_day").toString());
            return new Record(userId, bookId, borrowDay, returnDay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

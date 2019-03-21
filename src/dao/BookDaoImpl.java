package dao;

import main.Book;
import main.ISBN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 16:24
 * Original author: Nocturne
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public void addBook(Book book) {
        try {
            TransferBook tf = new TransferBook(book);
            Connection connect = getConnection();
            String name = tf.getName();
            String checkSql = "SELECT book_amount FROM Book WHERE book_name = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, name);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int amount = rst.getInt("book_amount") + 1;
                String updateSql = "UPDATE Book SET book_amount = ? WHERE book_name = ?";
                Object[] parm = new Object[2];
                parm[0] = amount;
                parm[1] = name;
                executeSQL(updateSql, parm);
            }
            else {
                String insertSql = "INSERT INTO book VALUES (null, ?, ?, ?, ?, ?, ?)";
                Object[] parm = new Object[6];
                parm[0] = tf.getName();
                parm[1] = tf.getIsbn().toString();
                parm[2] = tf.getPrice();
                parm[3] = tf.getAuthor();
                parm[4] = 1;
                parm[5] = 1;
                executeSQL(insertSql, parm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void TakeOneBook(Book book) {

    }

    @Override
    public Book getBookByISBN(ISBN isbn) {
        return null;
    }

    @Override
    public ArrayList<Book> getBookByKeyWord(String keyWord) {
        return null;
    }
}

package dao.book_dao;

import dao.BaseDao;
import base_data.Book;
import base_data.ISBN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, book.getIsbn().toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int amount = rst.getInt("book_amount") + 1;
                String updateSql = "UPDATE Book SET book_amount = ? WHERE book_isbn = ?";
                Object[] param = new Object[2];
                param[0] = amount;
                param[1] = book.getIsbn().toString();
                executeSQL(updateSql, param);
            }
            else {
                String insertSql = "INSERT INTO book VALUES (null, ?, ?, ?, ?, ?, ?)";
                Object[] param = new Object[6];
                param[0] = book.getName();
                param[1] = book.getIsbn().toString();
                param[2] = book.getPrice();
                param[3] = book.getAuthor();
                param[4] = 1;
                param[5] = 1;
                executeSQL(insertSql, param);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int checkLeft(Book book) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT book_amount FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, book.getIsbn().toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                return rst.getInt("book_amount");
            }
            else {
                throw new IllegalArgumentException("Book Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void takeOneBook(Book book) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT book_left FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, book.getIsbn().toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int left = rst.getInt("book_left");
                String takeSql = "UPDATE Book SET book_left = ? WHERE book_isbn = ?";
                Object[] param = new Object[2];
                param[0] = left - 1;
                param[1] = book.getIsbn().toString();
                executeSQL(takeSql, param);
            } else {
                throw new IllegalArgumentException("Book Not Exist!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookByISBN(ISBN isbn) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, isbn.toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                String book_name = rst.getString("book_name");
                double book_price = rst.getDouble("book_price");
                String book_author = rst.getString("book_author");
                return new Book(isbn, book_name, book_author, book_price);
            }
            else {
                throw new IllegalArgumentException("The Book Not Exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Book> getBooksByKeyWord(String keyWord) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_name LIKE ? OR book_author LIKE ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            String pattern = "%" + keyWord + "%";
            check.setObject(1, pattern);
            check.setObject(2, pattern);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                ArrayList<Book> books = new ArrayList<>();
                String isbn = rst.getString("book_isbn");
                books.add(getBookByISBN(new ISBN(isbn)));
                while (rst.next()) {
                    isbn = rst.getString("book_isbn");
                    books.add(getBookByISBN(new ISBN(isbn)));
                }
                return books;
            }
            else {
                throw new IllegalArgumentException("The Book Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getIdByISBN(ISBN isbn) throws IllegalArgumentException {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT book_id FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, isbn.toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                return rst.getInt("book_id");
            } else {
                throw new IllegalArgumentException("ISBN Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Book getBookById(int id) throws IllegalArgumentException{
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, id);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                String isbn = rst.getString("book_isbn");
                return getBookByISBN(new ISBN(isbn));
            }
            else {
                throw new IllegalArgumentException("ID Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

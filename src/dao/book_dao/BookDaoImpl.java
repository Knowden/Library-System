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
            Connection connection = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connection.prepareStatement(checkSql);
            check.setObject(1, book.getIsbn().toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int amount = rst.getInt("book_amount") + 1;
                String updateSql = "UPDATE Book SET book_amount = ? WHERE book_isbn = ?";
                executeSQL(updateSql, amount, book.getIsbn().toString());
            }
            else {
                String insertSql = "INSERT INTO book VALUES (null, ?, ?, ?, ?, ?, ?)";
                executeSQL(insertSql, book.getName(), book.getIsbn().toString(), book.getPrice(), book.getAuthor(), 1, 1);
            }
            closeAll(connection, check, rst);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putBook(ISBN isbn) {
        try {
            int left = checkLeft(isbn);
            String putSql = "UPDATE Book SET book_left = ? WHERE book_isbn = ?";
            executeSQL(putSql, left + 1, isbn.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int checkLeft(ISBN isbn) throws IllegalArgumentException {
        try {
            Connection connection = getConnection();
            String checkSql = "SELECT book_amount FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connection.prepareStatement(checkSql);
            check.setObject(1, isbn.toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int left = rst.getInt("book_amount");
                closeAll(connection, check, rst);
                return left;
            }
            else {
                closeAll(connection, check, rst);
                throw new IllegalArgumentException("Book Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void takeOneBook(ISBN isbn) throws IllegalArgumentException {
        try {
            Connection connection = getConnection();
            String checkSql = "SELECT book_left FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connection.prepareStatement(checkSql);
            check.setObject(1, isbn.toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                int left = rst.getInt("book_left");
                String takeSql = "UPDATE Book SET book_left = ? WHERE book_isbn = ?";
                executeSQL(takeSql, left - 1, isbn.toString());
                closeAll(connection, check, rst);
            } else {
                closeAll(connection, check, rst);
                throw new IllegalArgumentException("Book Not Exist!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookByISBN(ISBN isbn) throws IllegalArgumentException {
        try {
            Connection connection = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_isbn = ?";
            PreparedStatement check = connection.prepareStatement(checkSql);
            check.setObject(1, isbn.toString());
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                String bookName = rst.getString("book_name");
                double bookPrice = rst.getDouble("book_price");
                String bookAuthor = rst.getString("book_author");
                closeAll(connection, check, rst);
                return new Book(isbn, bookName, bookAuthor, bookPrice);
            }
            else {
                closeAll(connection, check, rst);
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
            Connection connection = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_name LIKE ? OR book_author LIKE ?";
            String pattern = "%" + keyWord + "%";
            PreparedStatement check = connection.prepareStatement(checkSql);
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
                closeAll(connection, check, rst);
                return books;
            }
            else {
                closeAll(connection, check, rst);
                throw new IllegalArgumentException("The Book Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookById(int bookId) {
        try {
            Connection connect = getConnection();
            String checkSql = "SELECT * FROM Book WHERE book_id = ?";
            PreparedStatement check = connect.prepareStatement(checkSql);
            check.setObject(1, bookId);
            ResultSet rst = check.executeQuery();
            if (rst.next()) {
                ISBN isbn = new ISBN(rst.getString("book_isbn"));
                String bookName = rst.getString("book_name");
                String bookAuthor = rst.getString("book_author");
                double bookPrice = rst.getDouble("book_price");
                closeAll(connect, check, rst);
                return new Book(isbn, bookName, bookAuthor, bookPrice);
            }
            else {
                closeAll(connect, check, rst);
                throw new IllegalArgumentException("The Book Not Exist!");
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
                int bookId =  rst.getInt("book_id");
                closeAll(connect, check, rst);
                return bookId;
            } else {
                closeAll(connect, check, rst);
                throw new IllegalArgumentException("ISBN Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

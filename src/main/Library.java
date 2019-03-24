package main;

import base_data.*;
import dao.book_dao.BookDaoImpl;
import dao.record_dao.RecordDAOImpl;
import java.util.ArrayList;

public class Library {

    private String name;

    private BookDaoImpl bImpl = new BookDaoImpl();
    private RecordDAOImpl rImpl = new RecordDAOImpl();

    public void setName(String name) {
        this.name = name;
    }

    public Book getBookByIsbn(ISBN isbn) throws IllegalArgumentException {
        return bImpl.getBookByISBN(isbn);
    }

    public ArrayList<Book> getByKeyWord(String keyWord) {
        return bImpl.getBooksByKeyWord(keyWord);
    }

    public ArrayList<Record> getOneBooks(int userId) {
        return rImpl.checkOneRecords(userId);
    }

    public void addBook(Book addBook) throws IllegalArgumentException {
        if (addBook.getClass() != Book.class) {
            throw new IllegalArgumentException("Book Info Is Wrong!");
        }
        else {
            bImpl.addBook(addBook);
        }
    }

    public void borrowBook(ISBN isbn, User user, Date today) throws IllegalArgumentException {

        if (bImpl.checkLeft(isbn) <= 0) {
            throw new IllegalArgumentException("This Book Not Left!");
        }

        int userId = user.getUserId();
        int bookId = bImpl.getIdByISBN(isbn);

        Record borrowRecord = new Record(userId, bookId, today);

        rImpl.addRecord(borrowRecord);
        bImpl.takeOneBook(isbn);
    }

    public void returnBook(ISBN isbn, User user) throws IllegalArgumentException {
        int userId = user.getUserId();
        int bookId = bImpl.getIdByISBN(isbn);

        rImpl.deleteRecord(userId, bookId);
        bImpl.putBook(isbn);
    }

    @Override
    public String toString() {
        return String.format("%s Library", name);
    }
}

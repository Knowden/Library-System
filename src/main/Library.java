package main;

import base_data.*;
import dao.book_dao.BookDaoImpl;
import dao.record_dao.RecordDAOImpl;

import java.util.ArrayList;
public class Library {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Book getBookByIsbn(ISBN isbn) throws IllegalArgumentException{
        if (!isbn.checkISBN()) {
            throw new IllegalArgumentException("ISBN Illegal");
        }
        BookDaoImpl impl = new BookDaoImpl();
        return impl.getBookByISBN(isbn);
    }

    public ArrayList<Book> getByKeyWord(String keyWord) {
        BookDaoImpl impl = new BookDaoImpl();
        return impl.getBooksByKeyWord(keyWord);
    }

    public void addBook(Book addBook) throws IllegalArgumentException{
        if (addBook.getClass() != Book.class) {
            throw new IllegalArgumentException("Book Info Is Wrong!");
        }
        else {
            BookDaoImpl impl = new BookDaoImpl();
            impl.addBook(addBook);
        }
    }

    public void borrowBook(User borrower, Book toBorrow, Date date) throws IllegalArgumentException {
        BookDaoImpl bImpl = new BookDaoImpl();
        if (bImpl.checkLeft(toBorrow) <= 0) {
            throw new IllegalArgumentException("This Book Not Left!");
        }

        bImpl.takeOneBook(toBorrow);

        int bookId = bImpl.getIdByISBN(toBorrow.getIsbn());
        Record borrowRecord = new Record(borrower.getUserId(), bookId, date);

        RecordDAOImpl rImpl = new RecordDAOImpl();
        rImpl.addRecord(borrowRecord);
    }

    public void returnBook(Record borrowRecord) throws IllegalArgumentException{
        RecordDAOImpl rImpl = new RecordDAOImpl();
        rImpl.deleteRecord(borrowRecord);
        int bookId = borrowRecord.getBookId();
        BookDaoImpl bImpl = new BookDaoImpl();
        Book returnBook = bImpl.getBookById(bookId);
        bImpl.addBook(returnBook);
    }

    @Override
    public String toString() {
        return String.format("%s Library", name);
    }

}

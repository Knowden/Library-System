package dao.book_dao;

import base_data.Book;
import base_data.ISBN;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 16:20
 * Original author: Nocturne
 */
public interface BookDao {

    public void addBook(Book book);

    public void putBook(ISBN isbn);

    public void takeOneBook(ISBN isbn);

    public Book getBookByISBN(ISBN isbn);

    public ArrayList<Book> getBooksByKeyWord(String keyWord);

    public Book getBookById(int bookId);

    public int checkLeft(ISBN isbn);

    public int getIdByISBN(ISBN isbn);

}

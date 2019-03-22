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

    public void takeOneBook(Book book);

    public Book getBookByISBN(ISBN isbn);

    public ArrayList<Book> getBooksByKeyWord(String keyWord);

    public int checkLeft(Book book);

    public int getIdByISBN(ISBN isbn);

    public Book getBookById(int id);

}

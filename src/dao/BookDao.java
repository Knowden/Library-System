package dao;

import main.Book;
import main.ISBN;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 16:20
 * Original author: Nocturne
 */
public interface BookDao {

    public void addBook(Book book);

    public void TakeOneBook(Book book);

    public Book getBookByISBN(ISBN isbn);

    public ArrayList<Book> getBookByKeyWord(String keyWord);

}

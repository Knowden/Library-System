package dao.book_dao;

import dao.book_dao.BookDaoImpl;
import base_data.Book;
import base_data.ISBN;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-21 11:34
 * Original author: Nocturne
 */
public class BookDaoImplTest {

    @Test
    public void addBook() {
        BookDaoImpl impl = new BookDaoImpl();
        impl.addBook(new Book(new ISBN("9787312036033"), "world", "haha", 123));
        impl.addBook(new Book(new ISBN("9787533468538"), "Knowden", "haha", 123));
        impl.addBook(new Book(new ISBN("9787538724523"), "Nocturne", "haha", 34));
        impl.addBook(new Book(new ISBN("9787040266511"), "hello", "haha", 645));
    }

    @Test
    public void getBookByKeyWord() {
        BookDaoImpl impl = new BookDaoImpl();
        ArrayList<Book> books = impl.getBooksByKeyWord("haha");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------------");
        }
        System.out.println("===================================================");
        books = impl.getBooksByKeyWord("ha");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------------");
        }
        System.out.println("===================================================");
        books = impl.getBooksByKeyWord("n");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------------");
        }
        System.out.println("===================================================");
        books = impl.getBooksByKeyWord("wocaonima");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------------");
        }
    }

    @Test
    public void getBookByISBN() {
        BookDaoImpl impl = new BookDaoImpl();
        Book book = impl.getBookByISBN(new ISBN("9787040266511"));
        System.out.println(book);
        System.out.println("--------------------------------");
        book = impl.getBookByISBN(new ISBN("9787538724523"));
        System.out.println(book);
        book = impl.getBookByISBN(new ISBN("7532542181"));
        System.out.println(book);
    }
}
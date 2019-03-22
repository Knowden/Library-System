package main;

import base_data.Book;
import base_data.ISBN;
import base_data.User;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 11:04
 * Original author: Nocturne
 */
public class LibraryTest {

    private static Library library = new Library();

    @org.junit.BeforeClass
    public static void prepare() {
        library.addBook(new Book(new ISBN("9787040266511"), "hello", "haha",123));
        library.addBook(new Book(new ISBN("9787312036033"), "world", "haha", 12));
        library.addBook(new Book(new ISBN("9787533468538"), "Knowden", "haha", 23));
        library.addBook(new Book(new ISBN("9787538724523"), "Nocturne", "haha", 3));
    }

    @org.junit.Test
    public void getBookByIsbn() {
        Book book = library.getBookByIsbn(new ISBN("9787040266511"));
        System.out.println(book);
    }

    @org.junit.Test
    public void getByKeyWord() {
        ArrayList<Book> books = library.getByKeyWord("haha");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("----------------");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        books = library.getByKeyWord("Nocturne");
        for (Book book : books) {
            System.out.println(book);
            System.out.println("----------------");
        }
    }

    @org.junit.Test
    public void addBook() {
    }

    @org.junit.Test
    public void borrowBook() {
        Book book = library.getBookByIsbn(new ISBN("9787040266511"));
        library.borrowBook(new User("123",123), book, Server.today);
    }

    @org.junit.Test
    public void returnBook() {
    }

}
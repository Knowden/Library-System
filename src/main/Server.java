package main;

import base_data.*;
import dao.book_dao.BookDaoImpl;

import java.util.ArrayList;

public class Server {

    private Library library = new Library();
    private UserCenter center = new UserCenter();

    public static Date today = new Date(2019, 3, 20);

    public void addBook(String isbn, String name, String author, double price) {
        Book addBook = new Book(new ISBN(isbn), name, author, price);
        library.addBook(addBook);
    }

    public void borrowBook(String isbn, User user) {
        Book borrowBook = new Book(new ISBN(isbn));
        library.borrowBook(user, borrowBook, today);
    }

    public void returnBook(ISBN isbn, User user) {
        BookDaoImpl impl = new BookDaoImpl();
        int bookId = impl.getIdByISBN(isbn);
        Record record = new Record(user.getUserId(), bookId, today);
        library.returnBook(record);
    }

    public ArrayList<Book> inquireBooks(InquireType type, Object obj)
            throws IllegalArgumentException{
        switch (type) {
            case ISBN: {
                try {
                    ISBN isbn = (ISBN) obj;
                    return inquireBooks(isbn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case KEYWORD: {
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("String is need!");
                }
                try {
                    String keyWord = (String) obj;
                    return inquireBooks(keyWord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default: {
                throw new IllegalArgumentException("INQUIRE TYPE ERROR!");
            }
        }
    }

    private ArrayList<Book> inquireBooks(ISBN isbn) {
        ArrayList<Book> result = new ArrayList<>();
        result.add(library.getBookByIsbn(isbn));
        return result;
    }

    private ArrayList<Book> inquireBooks(String keyWord) {
        return library.getByKeyWord(keyWord);
    }

    public void addUser(String name, String passWord, int id) {
        User toAdd = new User(name, passWord, id);
        center.addUser(toAdd);
    }

    public void deleteUser(int id) {
        User toDelete = new User(id);
        center.deleteUser(toDelete);
    }

    public User login(String name, String passWord) {
        User toLogin = center.getUser(name);
        if (toLogin.getPassWord().equals(passWord)) {
            return toLogin;
        }
        return null;
    }
}

enum InquireType {
    ISBN, KEYWORD
}
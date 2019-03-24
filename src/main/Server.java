package main;

import base_data.*;

import java.util.ArrayList;

public class Server {

    private Library library = new Library();
    private UserCenter center = new UserCenter();

    public static Date today = new Date(2019, 3, 20);

    public void addBook(String isbn, String name, String author, double price) {
        Book addBook = new Book(new ISBN(isbn), name, author, price);
        library.addBook(addBook);
    }

    public void borrowBook(String isbnString, User user) {
        ISBN isbn = new ISBN(isbnString);
        library.borrowBook(isbn, user, today);
    }

    public void returnBook(String isbnString, User user) {
        ISBN isbn = new ISBN(isbnString);
        library.returnBook(isbn, user);
    }

    public ArrayList<Book> inquireBooks(ISBN isbn) {
        ArrayList<Book> result = new ArrayList<>();
        result.add(library.getBookByIsbn(isbn));
        return result;
    }

    public ArrayList<Book> inquireBooks(String keyWord) {
        return library.getByKeyWord(keyWord);
    }

    public void addUser(String name, String passWord, int id) {
        User toAdd = new User(name, passWord, id);
        center.addUser(toAdd);
    }

    public ArrayList<Record> checkOneRecords(User user) {
        int userId = user.getUserId();
        return library.getOneBooks(userId);
    }

    public void deleteUser(int userId) {
        center.deleteUser(userId);
    }

    public User login(String name, String passWord) {
        User toLogin = center.getUser(name);
        if (toLogin.getPassWord().equals(passWord)) {
            return toLogin;
        }
        return null;
    }
}
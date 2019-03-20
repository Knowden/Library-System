package main;

import command.AddCommand;
import command.BorrowCommand;
import command.Command;
import command.ReturnCommand;

import java.util.ArrayList;

public class Server {

    private ArrayList<Library> libraries = new ArrayList<>();

    public static Date today = new Date(2019, 3, 20);

    public void addLib(Library lib) {
        libraries.add(lib);
    }

    public void addBook(Book book, int index) {
        Library chooseLib = libraries.get(index);
        Command add = new AddCommand(chooseLib);
        add.execute(book);
    }

    public void borrowBook(Book book, int index, User user) {
        Library chooseLib = libraries.get(index);
        Command command = new BorrowCommand(user, chooseLib);
        command.execute(book);
    }

    public void returnBook(Book book, int index, User user) {
        Library chooseLib = libraries.get(index);
        Command command = new ReturnCommand(user, chooseLib);
        command.execute(book);
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
        for (Library library : libraries) {
            result.add(library.getBookByIsbn(isbn));
        }
        return result;
    }

    private ArrayList<Book> inquireBooks(String keyWord) {
        ArrayList<Book> result = new ArrayList<>();
        for (Library library : libraries) {
            result.addAll(library.getByKeyWord(keyWord));
        }
        return result;
    }


}

enum InquireType {
    ISBN, KEYWORD
}
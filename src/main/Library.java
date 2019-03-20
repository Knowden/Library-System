package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    private String name;
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<Book, Integer> amount = new HashMap<>();
    private HashMap<Book, Integer> left = new HashMap<>();
    private HashMap<Book, ArrayList<Record>> records = new HashMap<>();

    public Library(String name) {
        super();
        this.name = name;
    }

    public Book getBookByIsbn(ISBN isbn) throws IllegalArgumentException{
        if (!isbn.checkISBN()) {
            throw new IllegalArgumentException("ISBN Illegal");
        }
        else {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
        }
        return null;
    }

    public ArrayList<Book> getByKeyWord(String keyWord) {
        ArrayList<Book> keyWordBooks = new ArrayList<>();
        Pattern key = Pattern.compile(keyWord);
        for (Book book : books) {
            Matcher matcher = key.matcher(book.getAuthor());
            if (matcher.find()) {
                keyWordBooks.add(book);
                continue;
            }

            matcher = key.matcher(book.getTitle());
            if (matcher.find()) {
                keyWordBooks.add(book);
                continue;
            }

            matcher = key.matcher(book.getIsbn().toString());
            if (matcher.find()) {
                keyWordBooks.add(book);
            }
        }
        return keyWordBooks;
    }

    public void addBook(Book addBook) throws IllegalArgumentException{
        if (addBook.getClass() != Book.class) {
            throw new IllegalArgumentException("Book Info Is Wrong!");
        }
        else {
            if (amount.containsKey(addBook)) {
                amount.put(addBook, amount.get(addBook) + 1);
                left.put(addBook, left.get(addBook) + 1);
            } else {
                amount.put(addBook, 1);
                left.put(addBook, 1);
                books.add(addBook);
            }
        }
    }

    public Record borrowBook(User borrower,Book toBorrow, Date date) throws IllegalArgumentException {
        if (!books.contains(toBorrow)) {
            throw new IllegalArgumentException("THE BOOK NOT EXIST!");
        }
        int leftNum = left.get(toBorrow);
        if (leftNum == 0) {
            throw new IllegalArgumentException("THERE IS NO LEFT!");
        }
        left.put(toBorrow, left.get(toBorrow) - 1);
        if (!records.containsKey(toBorrow)) {
            records.put(toBorrow, new ArrayList<>());
        }
        Record borrowReacord = new Record(borrower, toBorrow, date);
        records.get(toBorrow).add(borrowReacord);
        return borrowReacord;
    }

    public void returnBook(Record borrowRecord) throws IllegalArgumentException{
        Book borrowBook = borrowRecord.book();
        if (!books.contains(borrowBook)) {
            throw new IllegalArgumentException("BOOK IS NOT EXIST!");
        }
        if (records.get(borrowBook) != null) {
            if (!records.get(borrowBook).contains(borrowRecord)) {
                throw new IllegalArgumentException("NOT BORROW FROM HERE!");
            }
            records.get(borrowBook).remove(borrowRecord);
            left.put(borrowBook, left.get(borrowBook) + 1);
        }
        else {
            throw new IllegalArgumentException("NOT BORROW FROM HERE!");
        }
    }

    @Override
    public String toString() {
        return String.format("%s Library", name);
    }

}

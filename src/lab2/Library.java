package lab2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<ISBN, Integer> amount = new HashMap<>();

    public Library() {
        super();
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

            matcher = key.matcher(book.getAuthor());
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
        if (addBook.getClass() != Book.class || !addBook.isLegal()) {
            throw new IllegalArgumentException("Book info is Wrong!");
        }
        else {
            if (isInLab(addBook)) {
                amount.put(addBook.getIsbn(), amount.get(addBook.getIsbn()) + 1);
            } else {
                amount.put(addBook.getIsbn(), 1);
                books.add(addBook);
            }
        }
    }

    private boolean isInLab(Book findBook) {
        for (Book book : books) {
            if (book.equals(findBook)) {
                return true;
            }
        }
        return false;
    }

    public void changeAuthor(ISBN isbn, String author) throws IllegalArgumentException {
        if (!isbn.checkISBN() || author == null) {
            throw new IllegalArgumentException("ISBN WRONG!");
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getIsbn().equals(isbn)) {
                    Book updateBook = books.get(i);
                    updateBook.setAuthor(author);
                    books.set(i, updateBook);
                    break;
                }
            }
        }
    }

    public void changeTitle(ISBN isbn, String title) throws IllegalArgumentException {
        if (!isbn.checkISBN() || title == null) {
            throw new IllegalArgumentException("ISBN WRONG!");
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getIsbn().equals(isbn)) {
                    Book updateBook = books.get(i);
                    updateBook.setTitle(title);
                    books.set(i, updateBook);
                    break;
                }
            }
        }
    }

    public void changePrice(ISBN isbn, BigDecimal price) throws IllegalArgumentException {
        if (!isbn.checkISBN() || price == null) {
            throw new IllegalArgumentException("ISBN WRONG!");
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getIsbn().equals(isbn)) {
                    Book updateBook = books.get(i);
                    updateBook.setPrice(price);
                    books.set(i, updateBook);
                    break;
                }
            }
        }
    }



}

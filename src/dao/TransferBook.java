package dao;

import main.Book;
import main.ISBN;

/**
 * 21-oo
 * Created on:      2019-03-20 16:17
 * Original author: Nocturne
 */
public class TransferBook {

    private String name;
    private String author;
    private double price;
    private ISBN isbn;

    public TransferBook(Book book) {
        name = book.getTitle();
        author = book.getAuthor();
        price = Double.parseDouble(book.getPrice().toString());
        isbn = book.getIsbn();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

}

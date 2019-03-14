package lab2;

import java.math.BigDecimal;

public class Book {
    private ISBN isbn;
    private String title;
    private String author;
    private BigDecimal price;

    public Book() {
        super();
    }

    public Book(ISBN isbn, String title, String author, BigDecimal price) throws IllegalArgumentException{
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        if (!isLegal()) {
            throw new IllegalArgumentException("Argument Illegal!");
        }
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        System.out.println("Title: " + title);
        System.out.println("ISBN: " + isbn);
        System.out.println("Author：" + author);
        return ("Price: " + price);
    }

    public boolean equals(Book compareBook) {
        return isbn.equals(compareBook.isbn);
    }

    public boolean isLegal() {
        boolean isbnLegal = isbn.checkISBN();
        boolean authorLegal = ( author != null);
        boolean priceLegal = (price != null);
        boolean titleLegal = (title != null);
        return !(isbnLegal && authorLegal && priceLegal && titleLegal);
    }
}

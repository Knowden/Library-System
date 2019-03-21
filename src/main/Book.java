package main;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Book {
    private ISBN isbn;
    private String title;
    private String author;
    private BigDecimal price;
    private static ArrayList<Book> bookList = new ArrayList<>();
    
    private Book(ISBN isbn, String title, String author, BigDecimal price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static Book getInstance(ISBN isbn, String title, String author, BigDecimal price) throws IllegalArgumentException{
        if(isIllegal(isbn, title, author, price)) {
            throw new IllegalArgumentException("Book Info Error!");
        }
        for (Book book : bookList) {
            if(book.isbn.equals(isbn)) {
                
                return book;
            }
        }
        Book newBook = new Book(isbn, title, author, price);
        bookList.add(newBook);
        return newBook;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
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

    public static boolean isIllegal(ISBN isbn, String title, String author, BigDecimal price) {
        boolean isbnLegal = isbn.checkISBN();
        boolean authorIllegal = author == null;
        boolean priceIllegal = price == null;
        boolean titleIllegal = title == null;
        return !isbnLegal || authorIllegal || priceIllegal || titleIllegal;
    }
}

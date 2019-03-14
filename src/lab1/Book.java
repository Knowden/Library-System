package lab1;

import java.math.BigDecimal;

public class Book {
    private ISBN isbn;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer bookLeft;
    private Integer bookAmount;

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

    public Integer getBookLeft() {
        return bookLeft;
    }

    public Integer getBookAmount() {
        return bookAmount;
    }


    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
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

    public void setBookLeft(Integer bookLeft) {
        this.bookLeft = bookLeft;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }

    @Override
    public String toString() {
        System.out.println("Title: " + title);
        System.out.println("ISBN: " + isbn);
        System.out.println("Author：" + author);
        System.out.println("Price: " + price);
        System.out.println("Amount: " + bookAmount);
        return "Left: " + bookLeft;
    }
}

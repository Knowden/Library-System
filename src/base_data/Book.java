package base_data;

public class Book {
    private ISBN isbn;
    private String name = null;
    private String author = null;
    private Double price = null;

    public Book(ISBN isbn, String name, String author, double price) throws IllegalArgumentException {
        if (isIllegal(isbn, name, author, price)) {
            throw new IllegalArgumentException("Book Info Wrong!");
        }
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(ISBN isbn) {
        this.isbn = isbn;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String info = String.format("Name : %s\n", name);
        info += String.format("ISBN : %s\n", isbn);
        info += String.format("Author : %s\n", author);
        info += String.format("Price : %s", price);
        return info;
    }

    public boolean equals(Book compareBook) {
        return isbn.equals(compareBook.isbn);
    }

    public static boolean isIllegal(ISBN isbn, String name, String author, double price) {
        boolean isbnLegal = isbn.checkISBN();
        boolean authorIllegal = author == null;
        boolean priceIllegal = price < 0;
        boolean titleIllegal = name == null;
        return !isbnLegal || authorIllegal || priceIllegal || titleIllegal;
    }
}

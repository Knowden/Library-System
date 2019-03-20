package main;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 10:46
 * Original author: Nocturne
 */
public class Simulate {
    private static Server server = new Server();

    public static void main(String[] args) {
        server.addLib(new Library("ShaHe"));
        server.addBook(Book.getInstance(new ISBN("9787040266511"),"haha", "pity", BigDecimal.ONE), 0);
        ArrayList<Book> books = server.inquireBooks(InquireType.KEYWORD, "haha");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

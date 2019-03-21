package dao;

import main.Book;
import main.ISBN;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 21-oo
 * Created on:      2019-03-21 11:34
 * Original author: Nocturne
 */
public class BookDaoImplTest {

    @Test
    public void addBook() {
        BookDaoImpl impl = new BookDaoImpl();
        impl.addBook(Book.getInstance(new ISBN("9787312036033"), "world", "haha", BigDecimal.ONE));
        impl.addBook(Book.getInstance(new ISBN("9787533468538"), "Knowden", "haha", BigDecimal.ONE));
        impl.addBook(Book.getInstance(new ISBN("9787538724523"), "Nocturne", "haha", BigDecimal.ONE));
        impl.addBook(Book.getInstance(new ISBN("9787040266511"), "hello", "haha", BigDecimal.ONE));
    }
}
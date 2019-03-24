package base_data;

import java.util.regex.Pattern;

public class ISBN {
    private final String isbn;

    public ISBN(String isbn) throws IllegalArgumentException {
        this.isbn = isbn.replaceAll("-", "");
        if (!checkISBN()) {
            throw new IllegalArgumentException("Illegal ISBN");
        }
    }

    public boolean checkISBN() {
        if (isbn.length() == 10) {
            return check10();
        }
        else if (isbn.length() == 13) {
            return check13();
        }
        else {
            return false;
        }
    }

    private boolean check10() {
        if (!Pattern.matches("\\d{9}(\\d|X)", this.isbn)) {
            return false;
        }
        else {
            int checkSum = 0;
            for (int i = 0; i < 9; i++) {
                checkSum += (10 - i) * Character.digit(isbn.charAt(i), 10);
            }
            if (isbn.charAt(9) == 'X') {
                checkSum += 10;
            }
            else {
                checkSum += Character.digit(isbn.charAt(9), 10);
            }
            return checkSum % 11 == 0;
        }
    }

    private boolean check13() {
        if (!Pattern.matches("(978|979)\\d{10}", isbn)) {
            return false;
        }
        else {
            int checkSum = 0;
            for (int i = 0; i < 13; i += 2) {
                checkSum += Character.digit(isbn.charAt(i), 10);
            }
            for (int i = 1; i < 13; i += 2) {
                checkSum += 3 * Character.digit(isbn.charAt(i), 10);
            }
            return checkSum % 10 == 0;
        }
    }

    @Override
    public String toString() {
        return isbn;
    }

    public boolean equals(ISBN compareIsbn) {
        return isbn.equals(compareIsbn.isbn);
    }
}

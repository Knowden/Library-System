package main;

public class Record {
    private Book book;
    private User borrower;
    private Date borrowDate;
    private int leftDay;

    public Record(User borrower, Book book, Date borrowDate, int leftDay) {
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.leftDay = leftDay;
    }

    public Record(User borrower, Book book, Date borrowDate) {
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.leftDay = 30;
    }

    public Book book() {
        return book;
    }

    @Override
    public String toString() {
        String bookInfo = book.toString();
        Date exceptedDate = borrowDate.addDay(leftDay);
        return String.format("%s\n%s\nExpectedDate is %s",borrower.toString(), bookInfo, exceptedDate);
    }
}
package lab2;

public class Record {
    private Book book;
    private String borrower;
    private Date borrowDate;
    private int leftDay;

    public Record(String borrower, Book book, Date borrowDate, int leftDay) {
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.leftDay = leftDay;
    }

    @Override
    public String toString() {
        String bookInfo = book.toString();
        Date exceptedDate = borrowDate.addDay(leftDay);
        return String.format("%s\n%s\nExpectedDate is %s",borrower, bookInfo, exceptedDate);
    }
}
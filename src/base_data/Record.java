package base_data;

import dao.book_dao.BookDaoImpl;
import dao.user_dao.UserDAOImpl;

public class Record {
    private int bookId;
    private int userId;
    private Date borrowDate;
    private Date returnDate;

    public Record(int userId, int bookId, Date borrowDate, int borrowDays) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.addDay(borrowDays);
    }

    public Record(int userId, int bookId, Date borrowDate, Date returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Record(int userId, int bookId, Date borrowDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.addDay(30);
    }

    public int getBookId() {
        return bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        BookDaoImpl bImpl = new BookDaoImpl();
        Book book = bImpl.getBookById(bookId);
        UserDAOImpl uImpl = new UserDAOImpl();
        User user = uImpl.getUserById(userId);
        return String.format("%s\n%s\nExpectedDate is %s", user.toString(), book.toString(), returnDate);
    }
}
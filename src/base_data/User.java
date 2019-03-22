package base_data;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 08:49
 * Original author: Nocturne
 */
public class User {

    private String name;
    private String passWord;
    private int userId;

    public User(String name, String passWord, int userId) throws IllegalArgumentException{
        if (infoIllegal(name, userId)) {
            throw new IllegalArgumentException("User Info Error!");
        }
        this.name = name;
        this.passWord = passWord;
        this.userId = userId;
    }

    public User(int id) {
        this.userId = id;
    }

    private static boolean infoIllegal(String name, int stuId) {
        return name == null || stuId < 0 || name.equals("");
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassWord() {
        return passWord;
    }

    /*
    public void borrowBook(Book toBorrow, Date date) {
        borrowed.add(new Record(this, toBorrow, date));
    }

    public void borrowBook(Record borrowRecord) {
        this.borrowed.add(borrowRecord);
    }

    public Record getRecordByBook(Book borrowBook) throws IllegalArgumentException {
        for (Record record : borrowed) {
            if (record.book().equals(borrowBook)) {
                return record;
            }
        }
        throw new IllegalArgumentException("BOOK NOT FOUND!");
    }

    public void returnBook(Record returnRecord) throws IllegalArgumentException {
        if (!borrowed.contains(returnRecord)) {
            throw new IllegalArgumentException("BOOK NOT FOUND!");
        }
        borrowed.remove(returnRecord);
    }*/

    @Override
    public String toString() {
        return userId + "  " + name;
    }
}

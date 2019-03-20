package main;

import java.util.ArrayList;

/**
 * 21-oo
 * Created on:      2019-03-20 08:49
 * Original author: Nocturne
 */
public class User {

    private String name;
    private String stuId;
    private ArrayList<Record> borrowed = new ArrayList<>();

    private static ArrayList<User> userList = new ArrayList<>();

    private User(String name, String stuId) {
        this.name = name;
        this.stuId = stuId;
    }

    public static User getInstance(String name, String stuId) throws IllegalArgumentException{
        if (infoIllegal(name, stuId)) {
            throw new IllegalArgumentException("Info Error!");
        }
        for (User user : userList) {
            if (user.stuId.equals(stuId)) {
                return user;
            }
        }
        User newUser = new User(name, stuId);
        userList.add(newUser);
        return newUser;
    }

    private static boolean infoIllegal(String name, String stuId) {
        return name == null || stuId == null ||
                name.equals("") || stuId.equals("");
    }

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
    }

    @Override
    public String toString() {
        return String.format("%s %s",stuId, name);
    }
}

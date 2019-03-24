package main;

import base_data.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 21-oo
 * Created on:      2019-03-20 10:46
 * Original author: Nocturne
 */
public class Simulate {
    private static User user;
    private static Server server = new Server();
    private static Scanner keyBoard = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        while (user == null) {
            index();
        }
        welcome();
        while (true) {
            System.out.println("请输入您需要的功能:");
            System.out.println("1 借书 2 还书 3 书籍查询 4 查询已借书籍");
            String command = keyBoard.nextLine();
            if (command.equals("exit")) {
                break;
            }
            try {
                int type = Integer.parseInt(command);
                switch (type) {
                    case 1: {
                        borrowBook();
                        break;
                    }
                    case 2: {
                        returnBook();
                        break;
                    }
                    case 3: {
                        checkBook();
                        break;
                    }
                    case 4: {
                        checkRecords();
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Type Wrong!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("欢迎下次使用，再见");
    }

    private static void index() {
        System.out.println("请问您是否已有账号");
        System.out.println("已有账号 1  注册新用户 2  退出 3");
        try {
            int type = Integer.parseInt(keyBoard.nextLine());
            switch (type) {
                case 1: {
                    login();
                    break;
                }
                case 2: {
                    sign();
                    break;
                }
                case 3: {
                    System.exit(0);
                }
                default: {
                    throw new IllegalArgumentException("Type Error!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void login() {
        try {
            System.out.println("请输入用户名");
            String name = keyBoard.nextLine();

            System.out.println("请输入密码");
            String passWord = keyBoard.nextLine();

            user = server.login(name, passWord);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            System.out.println("用户名或密码错误，请重新输入");
        }
    }

    private static void sign() {
        try {
            while (user == null) {
                System.out.println("请输入您的用户名");
                String user_name = keyBoard.nextLine();
                System.out.println("请输入您的用户ID");
                int userId = Integer.parseInt(keyBoard.nextLine());
                System.out.println("请输入密码");
                String pwd1 = keyBoard.nextLine();
                System.out.println("请确认您的密码");
                String pwd2 = keyBoard.nextLine();
                if (pwd1.equals(pwd2)) {
                    user = new User(user_name, pwd1, userId);
                    server.addUser(user_name, pwd1, userId);
                }
                else {
                    System.out.println("两次密码不符");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void welcome() {
        System.out.println(user);
        System.out.println("欢迎使用图书馆管理系统，输入exit即可退出");
    }

    private static void borrowBook() {
        System.out.println("请输入图书的ISBN");
        String isbn = keyBoard.nextLine();
        try {
            server.borrowBook(isbn, user);
            System.out.println("借书成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void returnBook() {
        System.out.println("请输入要还书的ISBN");
        String isbn = keyBoard.nextLine();
        try {
            server.returnBook(isbn, user);
            System.out.println("还书成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkBook() {
        System.out.println("请输入要查询的方式 ： 1 ISBN  2 关键字");
        try {
            int type = Integer.parseInt(keyBoard.nextLine());
            System.out.println(type);
            if (type == 1) {
                checkByISBN();
            }
            else if (type == 2) {
                checkByKey();
            }
            else {
                System.out.println(type);
                throw new IllegalArgumentException("Type Wrong!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkByISBN() {
        System.out.println("请输入ISBN编码");
        String isbn = keyBoard.nextLine();
        ArrayList<Book> books = server.inquireBooks(new ISBN(isbn));
        for (Book book: books) {
            System.out.println(book);
            System.out.println("----------------------");
        }
    }

    private static void checkByKey() {
        System.out.println("请输入查询关键字");
        String keyWord = keyBoard.nextLine();
        ArrayList<Book> books = server.inquireBooks(keyWord);
        for (Book book : books) {
            System.out.println(book);
            System.out.println("----------------------");
        }
    }

    private static void checkRecords() {
        ArrayList<Record> records = server.checkOneRecords(user);
        for (Record record : records) {
            System.out.println(record);
            System.out.println("----------------------");
        }
    }
}

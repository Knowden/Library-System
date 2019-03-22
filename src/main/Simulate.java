package main;

import base_data.User;
import dao.user_dao.UserDAOImpl;

import java.util.Scanner;

/**
 * 21-oo
 * Created on:      2019-03-20 10:46
 * Original author: Nocturne
 */
public class Simulate {
    private static User user;
    private static Server server = new Server();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        while (user == null) {
            try {
                Scanner keyBoard = new Scanner(System.in);
                System.out.println("请输入用户名");
                String name = keyBoard.nextLine();

                UserDAOImpl impl = new UserDAOImpl();
                User checkUser = impl.getUserByName(name);

                System.out.println("请输入密码");
                String passWord = keyBoard.nextLine();

                if (checkUser.getPassWord().equals(passWord)) {
                    user = checkUser;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(user);
    }
}

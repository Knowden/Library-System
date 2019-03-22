package dao.user_dao;

import base_data.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    private static UserDAOImpl impl = new UserDAOImpl();

    @Test
    public void getUserByName() {
        User dang = impl.getUserByName("pollux");
        System.out.println(dang);
        User noExist = impl.getUserByName("bucunzai");
        System.out.println(noExist);
    }

    @Test
    public void getUserById() {
        User kno = impl.getUserById(17231122);
        System.out.println(kno);
        kno = impl.getUserById(17231139);
        System.out.println(kno);
    }

    @Test
    public void addUser() {
        //impl.addUser(new User("Alan", "IDon", 17230000));
    }

    @Test
    public void deleteUser() {
        //impl.deleteUser(new User("Alan", "IDon", 17230000));
    }
}
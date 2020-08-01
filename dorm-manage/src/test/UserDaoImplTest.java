package test;

import dao.impl.UserDaoImpl;

/**
 * @author haishao
 * @create 2020-05-21 15:25
 * @discript :
 */
public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

    @org.junit.Test
    public void existuser() {
        System.out.println(userDao.existuser("马云"));
    }

    @org.junit.Test
    public void login() {
        System.out.println(userDao.login("马云","123"));
    }

    @org.junit.Test
    public void regist() {
        userDao.regist("马化腾","123","234524001@qq.com");
    }
}
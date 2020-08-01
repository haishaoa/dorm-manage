package service;

import dao.impl.UserDaoImpl;

/**
 * @author haishao
 * @create 2020-05-21 15:47
 * @discript :
 */
public class UserServiceImpl {
    private UserDaoImpl userDao = new UserDaoImpl();

    //用户是否存在
    public boolean existuser(String username) {
       return userDao.existuser(username);
    }

    //用户登录
    public boolean login(String username, String pwd) {
        return userDao.login(username,pwd);
    }

    //用户注册
    public boolean regist(String username, String pwd, String email) {
        return userDao.regist(username,pwd,email);
    }
}

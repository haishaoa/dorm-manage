package web;

import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author haishao
 * @create 2020-05-21 14:33
 * @discript :
 */
@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    //注册
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置返回值
        String result = null;

        //1.获取前端输入数据
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        String email = req.getParameter("email");

        //2.调用existuser()
        if (userService.existuser(username)){
            result = "用户名已存在";
        }else {
            //3.调用regist()
            userService.regist(username,pwd,email);
            result = "注册成功";
        }

        //4.获得输出流
        PrintWriter out = resp.getWriter();

        out.print(result);
    }

    //登录功能
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置返回值
        String result = null;

        //1.获取前端输入数据
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");

        //2.调用existuser()
        if (userService.existuser(username)){
            //3.调用login()
            if (userService.login(username,pwd)){
                result = "登录成功";
            }else {
                result = "密码错误";
            }
        }else {
            result = "用户名不存在";
        }

        //3.获取输出流
        PrintWriter out = resp.getWriter();
        out.print(result);

        //4.设置返回信息和session中数据
        req.setAttribute("mainPage", "admin/blank.jsp");

        HttpSession session = req.getSession();
        session.setAttribute("username",username);

    }

}















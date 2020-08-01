package web;

import dao.impl.DormBuildDaoImpl;
import pojo.DormBuild;
import pojo.Student;
import service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

/**
 * @author haishao
 * @create 2020-05-22 2:54
 * @discript :
 */
@WebServlet(name = "StudentServlet",urlPatterns = "/StudentServlet")
public class StudentServlet extends BaseServlet {
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    //搜素功能
    protected void studentsearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端信息
        String searchType = req.getParameter("searchType");
        String s_studentText = req.getParameter("s_studentText");

        //2.2 调用姓名、寝室查询
        List<Student> list = studentService.studentssearch(searchType,s_studentText);
        System.out.println(list);

        //3.绑定信息
        req.setAttribute("studentList",list);
        req.setAttribute("mainPage", "admin/student.jsp");

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }

    //删除信息
    protected void studentdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端信息
        String studentId = req.getParameter("studentId");

        //2.调用删除方法
        studentService.studentdelete(studentId);

        //3.调用list()方法-->跳转
        list(req,resp);
    }

    //修改信息
    protected void prechange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.得到前端信息
        String studentId = req.getParameter("studentId");

        //1.保存信息
        List<DormBuild> list = dormBuildDao.dormBuildList();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getDormBuildName().equals(list.get(j).getDormBuildName())){
                    list.remove(j);
                }
            }
        }

        //3.调用查找方法
        Student student = studentService.studentsearch(studentId);

        req.setAttribute("student", student);
        req.setAttribute("dormBuildList", list);
        req.setAttribute("mainPage", "admin/studentchange.jsp");

        //2.赋值
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    //修改信息
    protected void studentupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.得到前端信息
        String studentId = req.getParameter("studentId");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String dormBuildName = req.getParameter("dormBuildName");
        String dormName = req.getParameter("dormName");
        String tel = req.getParameter("tel");

        //2.调用修改方法
        studentService.studentupdate(studentId,name,sex,dormBuildName,dormName,tel);

        //3.调用list()方法
        list(req,resp);

    }

    protected void preadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.保存信息
        List<DormBuild> list = dormBuildDao.dormBuildList();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getDormBuildName().equals(list.get(j).getDormBuildName())){
                    list.remove(j);
                }
            }
        }

        req.setAttribute("dormBuildList", list);
        req.setAttribute("mainPage", "admin/studentSave.jsp");

        //2.赋值
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    protected void studentadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端页面传递的值
        String studentId = req.getParameter("studentId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        Double dormBuildId = Double.parseDouble(req.getParameter("dormBuildId"));
        String dormName = req.getParameter("dormName");
        String tel = req.getParameter("tel");

        Student student = new Student(studentId,password,dormBuildId,dormName,name,sex,tel);

        //2.调用添加方法
        studentService.studentadd(student);

        //3.调用list()方法
        list(req,resp);
    }

    //查找全部的学生信息
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用查找方法
        List<Student> studentList = studentService.studentList();

        //2.绑定信息
        req.setAttribute("studentList",studentList);
        req.setAttribute("mainPage", "admin/student.jsp");

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }
}

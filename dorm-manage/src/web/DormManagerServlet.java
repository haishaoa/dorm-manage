package web;

import dao.impl.DormBuildDaoImpl;
import pojo.DormBuild;
import pojo.DormManager;
import service.DormBuildServiceImpl;
import service.DormManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 16:14
 * @discript :
 */
@WebServlet(name = "DormManagerServlet",urlPatterns = "/DormManagerServlet")
public class DormManagerServlet extends BaseServlet {
    private DormManagerServiceImpl dormManagerService = new DormManagerServiceImpl();
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.前端页面数据
        String name = req.getParameter("s_dormManagerText");

        //2.调用方法
        List<DormManager> list = dormManagerService.findname(name);

        //2.返回参数
        req.setAttribute("dormManagerList", list);
        req.setAttribute("mainPage", "admin/dormManager.jsp");

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.前端页面数据
        String dormManagerId = req.getParameter("dormManagerId");

        //2.调用方法
        dormManagerService.delete(dormManagerId);

        //3.调用list()
        list(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String dormManagerId = req.getParameter("dormManagerId");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        Double dormBuildId = Double.parseDouble(req.getParameter("dormBuildId"));
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");

        //2.调用修改方法
        dormManagerService.update(dormManagerId,userName,password,dormBuildId,name,sex,tel);

        //3.调用list()
        list(req,resp);
    }

    protected void managerchange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String dormManagerId = req.getParameter("dormManagerId");

        //2.调用方法
        DormManager dormManager = dormManagerService.findmanager(dormManagerId);

        //3.返回值
        req.setAttribute("dormManager",dormManager);
        req.setAttribute("mainPage", "admin/dormManagerchange.jsp");

        //1.保存信息
        List<DormBuild> dormBuildList = dormBuildDao.dormBuildList();

        for (int i = 0; i < dormBuildList.size(); i++) {
            for (int j = i + 1; j < dormBuildList.size(); j++) {
                if (dormBuildList.get(i).getDormBuildName().equals(dormBuildList.get(j).getDormBuildName())){
                    dormBuildList.remove(j);
                }
            }
        }
        req.setAttribute("dormBuildList", dormBuildList);

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String dormManagerId = req.getParameter("dormManagerId");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        Double dormBuildId = Double.parseDouble(req.getParameter("dormBuildId"));
        System.out.println("dormBuildName--->" + dormBuildId);
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");

        //2.调用方法
        dormManagerService.save(dormManagerId,userName,password,dormBuildId,name,sex,tel);

        //3.调用list()
        list(req,resp);

    }

    protected void preSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.保存信息
        List<DormBuild> dormBuildList = dormBuildDao.dormBuildList();

        for (int i = 0; i < dormBuildList.size(); i++) {
            for (int j = i + 1; j < dormBuildList.size(); j++) {
                if (dormBuildList.get(i).getDormBuildName().equals(dormBuildList.get(j).getDormBuildName())){
                    dormBuildList.remove(j);
                }
            }
        }

        req.setAttribute("dormBuildList", dormBuildList);

        req.setAttribute("mainPage", "admin/dormManagerSave.jsp");
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }
    //1.调用方法

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用方法
        List list = dormManagerService.dormManagerList();


        //2.返回参数
        req.setAttribute("dormManagerList", list);
        req.setAttribute("mainPage", "admin/dormManager.jsp");

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }
}

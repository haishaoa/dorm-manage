package web;

import dao.impl.DormBuildDaoImpl;
import pojo.DormBuild;
import service.DormBuildServiceImpl;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-21 23:18
 * @discript :
 */
@WebServlet(name = "DormBuildServlet", urlPatterns = "/DormBuildServlet")
public class DormBuildServlet extends BaseServlet {
    private DormBuildServiceImpl dormBuildService = new DormBuildServiceImpl();

    //搜索宿舍
    protected void dormBuildsearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端页面传递的信息
        String dormBuildName = req.getParameter("s_dormBuildName");

        //2.调用搜索方法
        List<DormBuild> dormBuildSearchList = dormBuildService.dormBuildsearch(dormBuildName);

        //3.绑定信息
        req.setAttribute("dormBuildList", dormBuildSearchList);
        req.setAttribute("mainPage", "admin/dormBuild.jsp");

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    //删除宿舍
    protected void dormBuilddelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端页面传递的信息
        Double dormBuildId = Double.parseDouble(req.getParameter("dormBuildId"));

        //2.调用删除方法
        dormBuildService.dormBuilddelete(dormBuildId);

        //3.绑定值
        req.setAttribute("mainPage", "admin/dormBuild.jsp");

        //3.1.调用dormBuildService中的dormBuildList
        List<DormBuild> dormBuildList = dormBuildService.dormBuildList();
        req.setAttribute("dormBuildList", dormBuildList);

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    //保存修改
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dormBuildId = req.getParameter("dormBuildId");
        String dormBuildName = req.getParameter("dormBuildName");
        String detail = req.getParameter("detail");
        DormBuild dormBuild = new DormBuild(dormBuildName, detail);

        if (StringUtils.isNotEmpty(dormBuildId)) {
            dormBuild.setDormBuildId(Double.parseDouble(dormBuildId));
        }

        boolean falg = false;

        //1.调用修改方法dormBuildupdate()
        falg = dormBuildService.dormBuildupdate(dormBuild);

        if (falg) {
            req.getRequestDispatcher("DormBuildServlet?action=list").forward(req, resp);
        } else {
            req.setAttribute("dormBuild", dormBuild);
            req.setAttribute("error", "保存失败");
            req.setAttribute("mainPage", "dormBuild/dormBuildSave.jsp");
            req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
        }

    }

    //保存添加
    protected void saveadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dormBuildId = req.getParameter("dormBuildId");
        String dormBuildName = req.getParameter("dormBuildName");
        String detail = req.getParameter("detail");
        DormBuild dormBuild = new DormBuild(dormBuildName, detail);

        if (StringUtils.isNotEmpty(dormBuildId)) {
            dormBuild.setDormBuildId(Double.parseDouble(dormBuildId));
        }

        boolean falg = false;

        //2.调用添加方法dormBuildadd()
        falg = dormBuildService.dormBuildadd(dormBuild);

        if (falg) {
            req.getRequestDispatcher("DormBuildServlet?action=list").forward(req, resp);
        } else {
            req.setAttribute("dormBuild", dormBuild);
            req.setAttribute("error", "保存失败");
            req.setAttribute("mainPage", "dormBuild/dormBuildSave.jsp");
            req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
        }

    }

    //修改寝室楼信息
    protected void preSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受前端页面传递的信息
        Double dormBuildId = Double.parseDouble(req.getParameter("dormBuildId"));

        //2.调用修改或添加方法
        if (dormBuildId != 0) {
            DormBuild dormBuild = dormBuildService.dormBuildShow(dormBuildId);
            req.setAttribute("dormBuild", dormBuild);
        }

        //3.绑定值
        req.setAttribute("mainPage", "admin/dormBuildSave.jsp");

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    //添加寝室楼信息
    protected void prepSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //3.绑定值
        req.setAttribute("mainPage", "admin/dormBuildAdd.jsp");

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    //查找全部寝室信息
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用dormBuildService中的dormBuildList
        List<DormBuild> dormBuildList = dormBuildService.dormBuildList();

        //2.设置返回值
//        req.setAttribute("pageCode", 5);
        req.setAttribute("dormBuildList", dormBuildList);
        req.setAttribute("mainPage", "admin/dormBuild.jsp");

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

}



















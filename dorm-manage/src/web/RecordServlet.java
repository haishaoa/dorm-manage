package web;

import pojo.DormBuild;
import pojo.Record;
import pojo.Student;
import service.DormBuildServiceImpl;
import service.RecordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 23:12
 * @discript :
 */
@WebServlet(name = "RecordServlet",urlPatterns = "/RecordServlet")
public class RecordServlet extends BaseServlet {
    private RecordService recordService = new RecordService();
    private DormBuildServiceImpl dormBuildService = new DormBuildServiceImpl();

    protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String searchType = req.getParameter("searchType");
        String s_studentText = req.getParameter("s_studentText");

        //2.调用方法
        List<Record> list = recordService.search(searchType, s_studentText);

        //3.返回值
        req.setAttribute("recordList", list);

        //2.dormBuildList数组
        List<DormBuild> dormBuildList = dormBuildService.dormBuildList();
        req.setAttribute("dormBuildList", dormBuildList);

        req.setAttribute("mainPage", "dormManager/record.jsp");

        //4.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String recordId = req.getParameter("recordId");

        if (recordId.length() == 1){
            recordId = "000" + recordId;
        }else if (recordId.length() == 2){
            recordId = "00" + recordId;
        }else if (recordId.length() == 3){
            recordId = "0" + recordId;
        }else {
            recordId = "" + recordId;
        }

        //2.调用方法
        recordService.delete(recordId);

        //3.调用list()
        list(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String studentNumber = req.getParameter("studentNumber");
        String date = req.getParameter("date");
        String detail = req.getParameter("detail");
        String recordId = req.getParameter("recordId");

        //2.调用方法
        recordService.update(recordId,detail);

        //3.调用list()
        list(req,resp);
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String studentNumber = req.getParameter("studentNumber");
        String date = req.getParameter("date");
        String detail = req.getParameter("detail");

        //2.调用方法
        recordService.save(studentNumber,date,detail);

        //3.调用list()
        list(req,resp);
    }

    protected void preSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据
        String recordId = req.getParameter("recordId");

        //2.调用方法
        Record record = recordService.searchByrecordId(recordId);
        String studentNumber = record.getStudentNumber();
        Student student = recordService.searchBystudentId(studentNumber);
        req.setAttribute("student", student);
        req.setAttribute("record", record);

        //1.获取时间
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String sysDatetime = fmt.format(rightNow.getTime());

        //2.返回值
        req.setAttribute("date", sysDatetime);
        if (recordId == null){
            req.setAttribute("mainPage", "dormManager/recordSave.jsp");
        }else {
            req.setAttribute("mainPage", "dormManager/recordUpdate.jsp");
        }

        //3.跳转页面
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.recordList数组
        List<Record> recordList = recordService.list();
        req.setAttribute("recordList", recordList);

        //2.dormBuildList数组
        List<DormBuild> dormBuildList = dormBuildService.dormBuildList();
        req.setAttribute("dormBuildList", dormBuildList);

        req.setAttribute("mainPage", "dormManager/record.jsp");
        req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
    }
}

















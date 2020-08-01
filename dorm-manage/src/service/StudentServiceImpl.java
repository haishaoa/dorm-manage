package service;

import dao.impl.StudentDaoImpl;
import pojo.Student;

import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 3:25
 * @discript :
 */
public class StudentServiceImpl {
    private StudentDaoImpl studentDao = new StudentDaoImpl();

    //通过姓名\寝室查找\学号
    public List<Student> studentssearch(String type,String date){
        return studentDao.studentssearch(type,date);
    }

    //删除信息
    public void studentdelete(String studentId){
        studentDao.studentdelete(studentId);
    }

    //修改信息
    public void studentupdate(String studentId,String name,String sex,String dormBuildName,String dormName,String tel){
        studentDao.studentupdate(studentId,name,sex,dormBuildName,dormName,tel);
    }

    //通过学生id查询学生信息
    public Student studentsearch(String studentId){
        return studentDao.studentsearch(studentId);
    }

    //添加学生信息
    public void studentadd(Student student){
        studentDao.studentadd(student);
    }

    //查找全部学生信息
    public List<Student> studentList() {
        return studentDao.studentList();
    }
}

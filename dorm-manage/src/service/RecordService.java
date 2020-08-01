package service;

import dao.impl.DormBuildDaoImpl;
import dao.impl.RecordDaoImpl;
import dao.impl.StudentDaoImpl;
import pojo.Record;
import pojo.Student;

import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 23:39
 * @discript :
 */
public class RecordService {
    private RecordDaoImpl recordDao = new RecordDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    public List<Record> search(String searchType,String s_studentText){
        return recordDao.search(searchType,s_studentText);
    }

    public void delete(String recordId){
        recordDao.delete(recordId);
    }

    public void update(String recordId,String detail){
        recordDao.update(recordId,detail);
    }

    public Student searchBystudentId(String studentId){
        return studentDao.studentsearch(studentId);
    }

    public Record searchByrecordId(String recordId){
        return recordDao.searchByrecordId(recordId);
    }

    public void save(String studentNumber,String date,String detail){
        //1.引入记录编码
        String recordId = RecordService.number();

        //2.查询学生信息
        Student student = studentDao.studentsearch(studentNumber);
        String name = student.getName();
        String dormName = student.getDormName();
        Double dormBuildId = student.getDormBuildId();
        String dormBuildName = dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName();

        //3.给Record对象赋值
        Record record = new Record(recordId,studentNumber,name,date,detail,dormBuildId,dormBuildName,dormName);

        //4.调用save()
        recordDao.save(record);
    }

    public List<Record> list(){
        return recordDao.list();
    }

    //记录编码
    static int number = 0;
    public static String number(){
        number++;
        String str = null;

        if (number/1000 != 0){
            str = String.valueOf(number);
        }else if (number/100 != 0){
            str = "0" + number;
        }else if (number/10 != 0){
            str = "00" + number;
        }else {
            str = "000" + number;
        }

        return str;
    }
}

























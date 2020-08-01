package test;

import dao.impl.StudentDaoImpl;
import org.junit.Test;
import pojo.Student;

import static org.junit.Assert.*;

/**
 * @author haishao
 * @create 2020-05-22 8:49
 * @discript :
 */
public class StudentDaoImplTest {
    private StudentDaoImpl studentDao = new StudentDaoImpl();

    @Test
    public void studentadd() {
        Student student = new Student("171203725","123",1.0,"525","刘德华","男","13324082019");
        studentDao.studentadd(student);
    }

    @Test
    public void studentList() {
    }
}
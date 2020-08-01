package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import org.bson.Document;
import org.bson.conversions.Bson;
import pojo.Student;
import utils.JDBCUtils;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 3:01
 * @discript :
 */
public class StudentDaoImpl {
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    //删除信息
    public void studentdelete(String studentId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.确定删除对象
        Bson bson = Filters.eq("studentId", studentId);

        //3.执行删除语句
        mongoCollection.deleteOne(bson);
    }

    //修改信息
    public void studentupdate(String studentId,String name,String sex,String dormBuildName,String dormName,String tel){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.调用更新方法
        Bson bson = Filters.eq("studentId", studentId);

        //3.Document封装更新信息
        Document document = new Document("$set",new Document("name",name).append("sex",sex).append("dormBuildName",dormBuildName).append("dormName",dormName).append("tel",tel));

        //4.updateOne()方法一个参数是更新条件，第二个参数是更新的内容
        mongoCollection.updateOne(bson,document);
    }

    //通过姓名\寝室查找\学号
    public List<Student> studentssearch(String type,String date){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.根据形参进行查询
        BasicDBObject dbObject = new BasicDBObject();

        if ("name".equals(type)){
            dbObject.put("name", date);
        }else if ("dormName".equals(type)){
            dbObject.put("dormName", date);
        }else {
            dbObject.put("studentId", date);
        }


        //3.查询
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //4.将查询到的记录保存到list中
        List list = new ArrayList();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();
            Student student = new Student();

            //5.赋值
            student.setStudentId(document.getString("studentId"));
            Double dormBuildId = document.getDouble("dormBuildId");
            student.setDormBuildId(dormBuildId);
            student.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            student.setDormName(document.getString("dormName"));
            student.setName(document.getString("name"));
            student.setPassword(document.getString("password"));
            student.setSex(document.getString("sex"));
            student.setTel(document.getString("tel"));

            //6.添加
            list.add(student);
        }

        return list;
    }

    //通过学生id查询学生信息
    public Student studentsearch(String studentId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("studentId",studentId);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到Student对象中
        Student student = null;
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();
            student = new Student();

            //4.赋值
            student.setStudentId(document.getString("studentId"));
            student.setDormBuildId(document.getDouble("dormBuildId"));
            student.setDormBuildName(document.getString("dormBuildName"));
            student.setDormName(document.getString("dormName"));
            student.setName(document.getString("name"));
            student.setPassword(document.getString("password"));
            student.setSex(document.getString("sex"));
            student.setTel(document.getString("tel"));
        }

        return student;
    }

    //添加学生信息
    public void studentadd(Student student){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.调用添加语句
        Document document = new Document("studentId",student.getStudentId()).append("password",student.getPassword()).append("name",student.getName()).append("sex",student.getSex()).append("dormBuildId",student.getDormBuildId()).append("dormName",student.getDormName()).append("tel",student.getTel());
        List<Document> list = new ArrayList<>();

        list.add(document);
        mongoCollection.insertMany(list);
    }

    //查找全部学生信息
    public List<Student> studentList() {
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("student");

        //2.调用find()进行查询student表中的全部数据，获取迭代器
        FindIterable findIterable = mongoCollection.find();

        //3.将迭代出来的内容放入游标遍历
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        //4.创建存放学生的数组
        List<Student> list = new ArrayList<>();

        //5.循环数据，并将数据赋值给对应的Student对象
        while (mongoCursor.hasNext()){
            Student student = new Student();
            Document document = mongoCursor.next();

            //赋值
            student.setStudentId(document.getString("studentId"));
            Double dormBuildId = document.getDouble("dormBuildId");
            student.setDormBuildId(dormBuildId);
            student.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            student.setDormName(document.getString("dormName"));
            student.setName(document.getString("name"));
            student.setSex(document.getString("sex"));
            student.setStuNumber(document.getString("stuNum"));
            student.setTel(document.getString("tel"));
            student.setPassword(document.getString("password"));

            list.add(student);
        }

        return list;
    }
}














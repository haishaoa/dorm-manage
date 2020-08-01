package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import pojo.DormBuild;
import pojo.Record;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 23:29
 * @discript :
 */
public class RecordDaoImpl {
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    public List<Record> search(String searchType,String s_studentText){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.根据形参进行查询
        BasicDBObject dbObject = new BasicDBObject();

        if ("name".equals(searchType)){
            dbObject.put("studentName", s_studentText);
        }else if ("number".equals(searchType)){
            dbObject.put("studentNumber", s_studentText);
        }else {
            dbObject.put("dormName", s_studentText);
        }

        //3.查询
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //4.将查询到的记录保存到list中
        List list = new ArrayList();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();

            Record record = new Record();

            //5.赋值
            record.setRecordId(document.getString("recordId"));
            record.setStudentNumber(document.getString("studentNumber"));
            record.setStudentName(document.getString("studentName"));
            Double dormBuildId = document.getDouble("dormBuildId");
            record.setDormBuildId(dormBuildId);
            record.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            record.setDormName(document.getString("dormName"));
            record.setDate(document.getString("date"));
            record.setDetail(document.getString("detail"));

            list.add(record);
        }
        return list;
    }

    public void delete(String recordId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.确定删除对象
        Bson bson = Filters.eq("recordId", recordId);

        //3.执行删除语句
        mongoCollection.deleteOne(bson);
    }

    public void update(String recordId,String detail){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.调用更新方法
        Bson bson = Filters.eq("recordId", recordId);

        //3.Document封装更新信息
        Document document = new Document("$set",new Document("recordId",recordId).append("detail",detail));

        //4.updateOne()方法一个参数是更新条件，第二个参数是更新的内容
        mongoCollection.updateOne(bson,document);
    }

    public Record searchByrecordId(String recordId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("recordId",recordId);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到Record对象中
        Record record = new Record();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();

            //5.赋值
            record.setRecordId(document.getString("recordId"));
            record.setStudentNumber(document.getString("studentNumber"));
            record.setStudentName(document.getString("studentName"));
            Double dormBuildId = document.getDouble("dormBuildId");
            record.setDormBuildId(dormBuildId);
            record.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            record.setDormName(document.getString("dormName"));
            record.setDate(document.getString("date"));
            record.setDetail(document.getString("detail"));
        }

        return record;
    }

    public void save(Record record){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.得到插入信息
        Document document = new Document("recordId",record.getRecordId()).append("studentNumber",record.getStudentNumber()).append("studentName",record.getStudentName()).append("date",record.getDate()).append("detail",record.getDetail()).append("dormBuildId",record.getDormBuildId()).append("dormBuildName",record.getDormBuildName()).append("dormName",record.getDormName());

        //3.插入
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        mongoCollection.insertMany(documents);
    }

    public List<Record> list(){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("record");

        //2.得到数据
        FindIterable findIterable = mongoCollection.find();

        //3.将数据放入迭代器
        MongoCursor mongoCursor = findIterable.iterator();

        //4.遍历
        List<Record> list = new ArrayList<>();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();

            Record record = new Record();

            //5.赋值
            record.setRecordId(document.getString("recordId"));
            record.setStudentNumber(document.getString("studentNumber"));
            record.setStudentName(document.getString("studentName"));
            Double dormBuildId = document.getDouble("dormBuildId");
            record.setDormBuildId(dormBuildId);
            record.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            record.setDormName(document.getString("dormName"));
            record.setDate(document.getString("date"));
            record.setDetail(document.getString("detail"));

            list.add(record);
        }

        return list;
    }
}


















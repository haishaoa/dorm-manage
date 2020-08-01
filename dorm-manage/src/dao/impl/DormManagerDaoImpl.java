package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import pojo.DormBuild;
import pojo.DormManager;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 16:01
 * @discript :
 */
public class DormManagerDaoImpl {
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    //用户名查询
    public List<DormManager> findname(String name){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("name",name);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到数组中
        List<DormManager> list = new ArrayList<>();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();
            DormManager dormManager = new DormManager();

            //4.赋值
            dormManager.setDormManagerId(document.getString("dormManagerId"));
            dormManager.setUserName(document.getString("userName"));
            dormManager.setPassword(document.getString("password"));
            Double dormBuildId = document.getDouble("dormBuildId");
            dormManager.setDormBuildId(dormBuildId);
            dormManager.setDormBuildName(dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName());
            dormManager.setName(document.getString("name"));
            dormManager.setSex(document.getString("sex"));
            dormManager.setTel(document.getString("tel"));

            list.add(dormManager);
        }

        return list;
    }

    //删除数据
    public void delete(String dormManagerId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.确定删除对象
        Bson bson = Filters.eq("dormManagerId", dormManagerId);

        //3.执行删除语句
        mongoCollection.deleteOne(bson);
    }

    //数据更新
    public void update(String dormManagerId,String userName,String password,Double dormBuildId,String name,String sex,String tel){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.调用更新方法，更新宿舍信息
        Bson filter = Filters.eq("dormManagerId", dormManagerId);

        //3.Document封装更新的内容
        Document document = new Document("$set",new Document("dormManagerId",dormManagerId).append("userName",userName).append("password",password).append("dormBuildId",dormBuildId).append("name",name).append("sex",sex).append("tel",tel));

        //4.updateOne()方法一个参数是更新条件，第二个参数是更新的内容
        mongoCollection.updateOne(filter,document);
    }

    //通过dormManagerId查询
    public DormManager findmanager(String dormManagerId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("dormManagerId",dormManagerId);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到dormManager对象中
        DormManager dormManager = new DormManager();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();

            dormManager.setDormManagerId(document.getString("dormManagerId"));
            dormManager.setUserName(document.getString("userName"));
            dormManager.setPassword(document.getString("password"));
            dormManager.setDormBuildId(document.getDouble("dormBuildId"));
            dormManager.setDormBuildName(document.getString("dormBuildName"));
            dormManager.setName(document.getString("name"));
            dormManager.setSex(document.getString("sex"));
            dormManager.setTel(document.getString("tel"));
        }

        return dormManager;
    }

    //添加信息
    public void save(String dormManagerId,String userName,String password,Double dormBuildId,String dormBuildName,String name,String sex,String tel){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.得到插入信息
        Document document = new Document("dormManagerId",dormManagerId).append("userName",userName).append("password",password).append("dormBuildId",dormBuildId).append("dormBuildName",dormBuildName).append("name",name).append("sex",sex).append("tel",tel);
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        mongoCollection.insertMany(documents);
    }

    //查找全部信息
    public List dormManagerList(){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormManager");

        //2.执行查找语句
        FindIterable findIterable = mongoCollection.find();

        //3.将迭代出来的内容放入游标遍历
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        //5.创建存放宿舍的数组
        List<DormManager> list = new ArrayList<>();

        //6.循环数据，将数据赋值给对应的DormManager对象
        while (mongoCursor.hasNext()){
            DormManager dormManager = new DormManager();
            Document document = mongoCursor.next();

            //赋值
            dormManager.setDormManagerId(document.getString("dormManagerId"));
            dormManager.setUserName(document.getString("userName"));
            dormManager.setPassword(document.getString("password"));
            dormManager.setDormBuildId(document.getDouble("dormBuildId"));
            dormManager.setDormBuildName(document.getString("dormBuildName"));
            dormManager.setName(document.getString("name"));
            dormManager.setSex(document.getString("sex"));
            dormManager.setTel(document.getString("tel"));

            list.add(dormManager);
        }

        return list;
    }

}

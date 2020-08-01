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
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-21 22:10
 * @discript :
 */
public class DormBuildDaoImpl {
    //根据宿舍楼的编号删除
    public boolean dormBuilddelete(Double dormBuildId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //2.确定删除对象
        Bson bson = Filters.eq("dormBuildId", dormBuildId);

        //3.执行删除语句
        mongoCollection.deleteOne(bson);

        return true;
    }

    //添加宿舍楼信息
    public boolean dormBuildadd(DormBuild dormBuild){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //2.得到插入信息
        Document document = new Document("dormBuildId",dormBuild.getDormBuildId()).append("dormBuildName",dormBuild.getDormBuildName()).append("dormBuildDetail",dormBuild.getDetail());
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        mongoCollection.insertMany(documents);

        return true;
    }

    //修改宿舍楼信息
    public boolean dormBuildupdate(DormBuild dormBuild){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //2.调用更新方法，更新宿舍信息
        Bson filter = Filters.eq("dormBuildId", dormBuild.getDormBuildId());

        //3.Document封装更新的内容
        Document document = new Document("$set",new Document("dormBuildId",dormBuild.getDormBuildId()).append("dormBuildName",dormBuild.getDormBuildName()).append("dormBuildDetail",dormBuild.getDetail()));

        //4.updateOne()方法一个参数是更新条件，第二个参数是更新的内容
        mongoCollection.updateOne(filter,document);

        //5.更新结果
        return true;
    }

    //返回指定姓名的宿舍楼信息
    public List<DormBuild> dormBuildsearch(String dormBuildName){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("dormBuildName",dormBuildName);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到pojo对象中
        List<DormBuild> list = new ArrayList<>();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();
            DormBuild dormBuild = new DormBuild();

            dormBuild.setDormBuildId(document.getDouble("dormBuildId"));
            dormBuild.setDormBuildName(document.getString("dormBuildName"));
            dormBuild.setDetail(document.getString("dormBuildDetail"));

            list.add(dormBuild);
        }
        System.out.println(list);
        return list;
    }

    //返回单个宿舍楼信息
    public DormBuild dormBuildShow(Double dormBuildId){
        //1.连接数据库
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //2.根据形参值进行查询
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("dormBuildId",dormBuildId);
        FindIterable findIterable = mongoCollection.find(dbObject);
        MongoCursor mongoCursor = findIterable.iterator();

        //3.将查询到的记录保存到pojo对象中
        DormBuild dormBuild = new DormBuild();
        while (mongoCursor.hasNext()){
            Document document = (Document) mongoCursor.next();

            dormBuild.setDormBuildId(document.getDouble("dormBuildId"));
            dormBuild.setDormBuildName(document.getString("dormBuildName"));
            dormBuild.setDetail(document.getString("dormBuildDetail"));
        }

        return dormBuild;
    }

    //返回所有宿舍楼信息
    public List<DormBuild> dormBuildList(){
        //1.连接数据库
//        MongoDatabase mongoDatabase = JDBCUtils.getComMongodb();
        //2.选的访问的表
//        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("dormBuild");

        //1 2步改进
        MongoCollection mongoCollection = JDBCUtils.getComMongodb("dormBuild");

        //3.调用find()进行查询dormBuild表中的全部数据，获取迭代器
        FindIterable<Document> findIterable = mongoCollection.find();

        //4.将迭代出来的内容放入游标遍历
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        //5.创建存放宿舍的数组
        List<DormBuild> list = new ArrayList<>();

        //6.循环数据，将数据赋值给对应的DormBuild对象
        while (mongoCursor.hasNext()){
            DormBuild dormBuild = new DormBuild();
            Document document = mongoCursor.next();

            //赋值
            dormBuild.setDormBuildId(document.getDouble("dormBuildId"));
            dormBuild.setDormBuildName(document.getString("dormBuildName"));
            dormBuild.setDetail(document.getString("dormBuildDetail"));

            list.add(dormBuild);
        }

        return list;
    }
}




















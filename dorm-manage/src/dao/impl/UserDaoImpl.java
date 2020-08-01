package dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import pojo.User;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haishao
 * @create 2020-05-21 14:48
 * @discript :
 */
public class UserDaoImpl {
    public boolean existuser(String username) {
        //1.连接数据库
//        MongoDatabase mongoDatabase = JDBCUtils.getComMongodb();
//        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("user");
        MongoCollection<Document> mongoCollection = JDBCUtils.getComMongodb("user");

        //2.调用find()进行查询user表中的全部数据,获取迭代器
        FindIterable<Document> findIterable = mongoCollection.find();

        //3.将迭代出来的内容放入游标中遍历
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        //4.设置标识
        boolean falg = false;

        //5.循环数据,对比是否在标准有此用户名
        while (mongoCursor.hasNext()){
            Document document = mongoCursor.next();
//            document.getString("username");
            if (document.getString("username").equals(username)){
                falg = true;
                break;
            }
        }

        return falg;
    }

    public boolean login(String username, String pwd) {
        //设置标识
        boolean falg = false;

        //1.调用existuser()
        if (existuser(username)){
            //2.连接数据库
//            MongoDatabase mongoDatabase = JDBCUtils.getComMongodb();
//            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("user");

            MongoCollection<Document> mongoCollection = JDBCUtils.getComMongodb("user");

            //3.根据用户形参进行查询
            BasicDBObject dbObject = new BasicDBObject();
            dbObject.put("username",username);

            //4.查询
            FindIterable<Document> findIterable = mongoCollection.find(dbObject);
            MongoCursor<Document> mongoCursor = findIterable.iterator();

            while (mongoCursor.hasNext()){
                Document document = mongoCursor.next();

                //5.判断
                if (document.getString("password").equals(pwd)){
                    falg = true;
                    break;
                }
            }
        }

        return falg;
    }

    public boolean regist(String username, String pwd, String email) {
        //设置标识
        boolean falg = false;

        //给User赋值
        User user = new User();
        user.setUsername(username);
        user.setPwd(pwd);
        user.setEmail(email);

        //1.调用existuser()
        if (!existuser(username)){
            //2.连接数据库
//            MongoDatabase mongoDatabase = JDBCUtils.getComMongodb();
//            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("user");

            //2.连接数据库改进之后
            MongoCollection<Document> mongoCollection = JDBCUtils.getComMongodb("user");

            //3.插入数据
            Document document = new Document("username",user.getUsername()).append("password",user.getPwd()).append("email",user.getEmail());
            List<Document> list = new ArrayList<>();
            list.add(document);
            mongoCollection.insertMany(list);

            falg = true;
        }

        return falg;
    }
}













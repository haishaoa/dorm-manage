package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author haishao
 * @create 2020-05-21 14:41
 * @discript :
 */
public class JDBCUtils {
    //连接数据库
    public static MongoCollection getComMongodb(String table){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dorm");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(table);

        return mongoCollection;
    }
}

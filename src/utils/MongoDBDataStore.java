package utils;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MongoDBDataStore implements IDataStore {
    private static MongoDBDataStore instance = null;

    public static MongoDBDataStore getInstance() {
        if (instance == null) instance = new MongoDBDataStore();
        return instance;
    }

    private final MongoDatabase db;

    private MongoDBDataStore() {
        db = new MongoClient().getDatabase("database");
    }

    @Override
    public <T> void addToCollection(String collectionName, T element, Class<T> clazz) {
        createCollection(collectionName);
        var gson   = new Gson();
        var newDoc = Document.parse(gson.toJson(element));
        db.getCollection(collectionName).insertOne(newDoc);
    }

    @Override
    public <T> List<T> getCollection(String collectionName, Class<T> clazz) {
        createCollection(collectionName);
        var gson = new Gson();
        var docs = db.getCollection(collectionName).find();
        var collection = new ArrayList<T>();
        for (var doc : docs) {
            collection.add(gson.fromJson(doc.toJson(), clazz));
        }
        return collection;
    }

    private void createCollection(String collectionName) {
        boolean collectionExists = false;
        for (var name : db.listCollectionNames()) {
            if (Objects.equals(name, collectionName)) {
                collectionExists = true;
                break;
            }
        }
        if (!collectionExists) db.createCollection(collectionName);
    }
}

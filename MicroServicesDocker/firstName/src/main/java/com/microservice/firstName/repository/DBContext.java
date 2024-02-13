package com.microservice.firstName.repository;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class DBContext {
    public static MongoCollection<Document> fetchCollection(String mongoUrl, String dbName, String collectionName) {

        return MongoClients.create(mongoUrl)
            .getDatabase(dbName)
            .withCodecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .automatic(true)
                        .build())))
            .getCollection(collectionName);

    }
}

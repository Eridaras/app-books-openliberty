package com.distribuida.config;

import com.mongodb.client.MongoCollection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import javax.net.ssl.SSLContext;


import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;


@ApplicationScoped
public class MongoProducer {

    @Inject
    @ConfigProperty(name = "mongo.dbhost")
    String dbHost;

    @Inject
    @ConfigProperty(name = "mongo.port")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.dbname")
    String dbName;

    @Produces
    @ApplicationScoped
    public MongoCollection<Document> mongoDatabase() {
        MongoClient mongo = new MongoClient(dbHost, port);
        MongoDatabase database = mongo.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection("books");
        return collection;
    }
}

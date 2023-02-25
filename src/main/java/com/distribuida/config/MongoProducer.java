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
    @ConfigProperty(name = "mongo.hostname", defaultValue = "localhost")
    String hostname;

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.dbname", defaultValue = "book")
    String dbName;


    @Produces
    public MongoClient createMongo(){
        return new MongoClient(new ServerAddress(hostname, port));
    }

    @Produces
    @ApplicationScoped
    public MongoCollection<Document> createDB(MongoClient cliente) {
        MongoDatabase db = cliente.getDatabase(dbName);
        MongoCollection<Document> book = db.getCollection("book");
        return book;
    }

    public void close(
            @Disposes MongoClient toClose) {
        toClose.close();
    }
}

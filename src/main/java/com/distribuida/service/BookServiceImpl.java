package com.distribuida.service;

import com.distribuida.db.Book;

import com.mongodb.client.result.DeleteResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class BookServiceImpl implements BookService{
    @Inject
    MongoCollection<Document> booksCollection;


    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Book book;
        for (Document document: booksCollection.find()) {
            book = new Book();
            book.setTitle(document.getString("title"));
            book.setAuthor(document.getString("author"));
            book.setPrice(document.getDouble("price"));
            book.setId(document.getObjectId("_id").toHexString());
            book.setIsbn(document.getString("isbn"));
            books.add(book);
        }
        return books;
    }

    @Override
    public Book findById(String id) {
        Book book = new Book();
        for (Document document: booksCollection.find()) {
            if (id.equals(document.getObjectId("_id").toHexString())){
                book = new Book();
                book.setTitle(document.getString("title"));
                book.setAuthor(document.getString("author"));
                book.setPrice(document.getDouble("price"));
                book.setId(document.getObjectId("_id").toHexString());
                book.setIsbn(document.getString("isbn"));

            }
        }
        return book;
    }

    @Override
    public Response createBook(Book book) {
        Document doc = new Document();
                doc.append("id", book.getId());
                doc.append("isbn", book.getIsbn());
                doc.append("title", book.getTitle());
                doc.append("author", book.getAuthor());
                doc.append("price", book.getPrice());
        booksCollection.insertOne(doc);
        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response updateBook(String id, Book book) {
        if (id.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else {
            Document filterByGradeId = new Document("_id", new ObjectId(id));
            Document updateDoc = new Document();
            updateDoc.append("title", book.getTitle())
                    .append("isbn", book.getIsbn())
                    .append("price", book.getPrice())
                    .append("author", book.getAuthor());

            booksCollection.updateOne(filterByGradeId, new Document("$set", updateDoc));
            return Response.status(Response.Status.OK).build();
        }
    }

    @Override
    public Response deleteBook(String id) {
        ObjectId oid;

        try {
            oid = new ObjectId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("[\"Invalid object id!\"]").build();
        }

        Document query = new Document("_id", oid);

        DeleteResult deleteResult = booksCollection.deleteOne(query);

        if (deleteResult.getDeletedCount() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("[\"_id was not found!\"]").build();
        }

        return Response.status(Response.Status.OK).entity(query.toJson()).build();
    }
}

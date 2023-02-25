package com.distribuida.db;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @BsonProperty(value = "id")
    private String id;

    @BsonProperty(value = "isbn")
    private String isbn;

    @BsonProperty(value = "title")
    private String title;

    @BsonProperty(value = "author")
    private String author;

    @BsonProperty(value = "price")
    private Double price;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

package com.distribuida.service;

import com.distribuida.db.Book;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface BookService {
    List<Book>  findAll();
    Book findById(String id);
    Response createBook(Book book);
    Response updateBook(String id, Book book);
    Response deleteBook(String id);
}

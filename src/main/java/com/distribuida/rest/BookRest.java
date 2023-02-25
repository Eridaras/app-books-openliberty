package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BookRest {
    @Inject
    BookService bookService;

    @POST
    @Path("/")
    public Response createBook(Book book){
        return bookService.createBook(book);
    }

    @GET
    @Path("/")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GET
    @Path("/{id}")
    public Book findById(@PathParam("id")String id){
        return bookService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response bookUpdate(@PathParam("id")String id, Book book){
        return bookService.updateBook(id, book);
    }

    @DELETE
    @Path("/{id}")
    public Response bookDelete(@PathParam("id")String id){
        return bookService.deleteBook(id);
    }

}

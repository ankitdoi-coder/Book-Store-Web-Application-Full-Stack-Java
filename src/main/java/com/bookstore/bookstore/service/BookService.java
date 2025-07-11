package com.bookstore.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.bookstore.Model.Book;

public interface BookService {
    List<Book> getAllBooks(); // ✅ TO Get All Books To the Home page

    void saveBook(Book book); // ✅ TO Add New Book

    // void deleteBookById(Long id); // ✅ To Delete a Book by Id

    void deleteBookByTitle(String title);  //to delete By title

    void updateBookByTitle(Book book);
    Optional<Book> getBookById(Long id);

}

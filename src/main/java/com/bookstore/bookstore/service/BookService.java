package com.bookstore.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.bookstore.Model.Book;

public interface BookService {
    List<Book> getAllBooks(); // ✅ TO Get All Books To the Home page

    void saveBook(Book book); // ✅ TO Add New Book

    void deleteBookById(Book book); // ✅ To Delete a Book by Id

    void updateBookById(Book book); // ✅To update

    Optional<Book> findById(Long id);  // ✅ TO get THe list of  book by Id

    
}

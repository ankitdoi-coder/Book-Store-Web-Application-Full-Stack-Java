package com.bookstore.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// Make sure the Book class exists at this location; if not, update the import to the correct package:
import com.bookstore.bookstore.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title); // if title is unique

    // OR if you allow multiple same titles
    List<Book> findAllByTitle(String title);

}

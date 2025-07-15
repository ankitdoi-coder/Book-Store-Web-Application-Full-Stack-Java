package com.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// Make sure the Book class exists at this location; if not, update the import to the correct package:
import com.bookstore.bookstore.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    

}

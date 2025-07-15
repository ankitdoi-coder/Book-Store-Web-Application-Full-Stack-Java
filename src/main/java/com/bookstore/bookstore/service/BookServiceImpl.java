package com.bookstore.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.bookstore.Model.Book;
import com.bookstore.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() { // ✅ Fixed method name
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    

    @Override
    public void deleteBookById(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void updateBookById(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update fields only if user provided
        if (book.getPrice() > 0) {
            existingBook.setPrice(book.getPrice());
        }
        if (book.getStock() > 0) {
            existingBook.setStock(book.getStock());
        }
        if (book.getImageUrl() != null && !book.getImageUrl().isEmpty()) {
            existingBook.setImageUrl(book.getImageUrl());
        }
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            existingBook.setAuthor(book.getAuthor());
        }

        bookRepository.save(existingBook);
    }

   

    @Override
    public Optional<Book> findById(Long id) {
       Optional<Book> book= bookRepository.findById(id);
       return book;
    }

}

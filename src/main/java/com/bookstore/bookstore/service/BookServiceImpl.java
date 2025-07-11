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

    // @Override
    // public void deleteBookById(Long id){
    // bookRepository.deleteById(id);
    // }

    @Override
    public void deleteBookByTitle(String title) {
        Optional<Book> bookOptional = bookRepository.findByTitle(title);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
        } else {
            System.out.println("❌ Book not found with title: " + title);
            // throw exception or show error message if needed
        }
    }

    @Override
    public void updateBookByTitle(Book book) {
        Book existingBook = bookRepository.findByTitle(book.getTitle())
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
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

}

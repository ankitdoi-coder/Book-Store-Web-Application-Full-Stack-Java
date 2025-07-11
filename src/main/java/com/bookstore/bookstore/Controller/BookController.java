package com.bookstore.bookstore.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.bookstore.Model.Book;
import com.bookstore.bookstore.service.BookService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Book> books = bookService.getAllBooks();
        System.out.println("📚 Total books fetched: " + books.size());
        for (Book b : books) {
            System.out.println("📷 Book: " + b.getTitle() + ", Image: " + b.getImageUrl());
        }
        model.addAttribute("books", books);
        return "home";
    }

    // To Add new Book
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook"; // it shows form to add new book
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/"; // redirect to home after saving
    }

    // TO delete A Book
    @GetMapping("/delete")
    public String deleteBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "deletebook"; // it also shows form to delete a book
    }

    // For Now we are just comenting it beacause i want to
    // @PostMapping("/delete") // delete book by title insted id
    // public String deleteBookById(@RequestParam Long id) {
    // bookService.deleteBookById(id); // new method below
    // return "redirect:/";
    // }

    @PostMapping("/delete")
    public String deleteBookByTitle(@RequestParam String title) {
        bookService.deleteBookByTitle(title);
        return "redirect:/";
    }

    // For Updatting the Book attributes(Price,stocks,etc.)
    @GetMapping("/update")
    public String updateBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "updatebook";
    }

    @PostMapping("/update")
    public String updateBookByTitle(@ModelAttribute Book book) {
        bookService.updateBookByTitle(book);
        return "redirect:/";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        // Get or create cart
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Get book from DB
        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
            cart.add(optionalBook.get());
            session.setAttribute("cart", cart);
            System.out.println("✅ Book added to cart: " + optionalBook.get().getTitle());
        } else {
            System.out.println("❌ Book not found with ID: " + id);
        }

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null)
            cart = new ArrayList<>();

        // Calculate total manually
        double total = 0;
        for (Book book : cart) {
            total += book.getPrice();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total); // send to template
        return "cart";
    }

}

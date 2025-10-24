package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listOfBooks(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        model.addAttribute("librarian", loggedInUser.getRole().equalsIgnoreCase("librarian"));
        return "books";
    }

    @GetMapping("/add_book")
    public String createBookForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/sign_in";
        }
        Book book = new Book();
        model.addAttribute("book", book);
        return "create_book";
    }
}

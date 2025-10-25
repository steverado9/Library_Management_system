package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    //go to the add book page
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

    //post a book after creation
    @PostMapping("/add_book")
    public String saveBook(@ModelAttribute("book") Book book,Model model,  HttpSession session) {
            bookService.saveBook(book);
            return "redirect:/books";
    }

    //display list of books
    @GetMapping("/books")
    public String listOfBooks(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("librarian", loggedInUser.getRole().equalsIgnoreCase("librarian"));
        return "books";
    }
}

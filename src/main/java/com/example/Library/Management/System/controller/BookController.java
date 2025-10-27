package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    //Edit a book
    @GetMapping("book/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "edit_book";
    }

    @PutMapping("book/edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute("book") Book book, Model model) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setISBN(book.getISBN());
        existingBook.setYearPublished(book.getYearPublished());
        existingBook.setAvailable(book.isAvailable());

        bookService.updateBook(existingBook);
        return "redirect:/books";
    }

    //Delete a book
    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    //display list of books
    @GetMapping("/books")
    public String listOfBooks(@RequestParam(value = "keyword", required = false) String keyword, Model model, HttpSession session) {
        List<Book> books;
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (keyword != null && !keyword.isEmpty()) {
            books = bookService.searchBooks(keyword);
        } else {
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("librarian", loggedInUser.getRole().equalsIgnoreCase("librarian"));
        return "books";
    }
    //get a single book using the id
    @GetMapping("/book/{id}")
    public String getOneBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "single_book";
    }
}

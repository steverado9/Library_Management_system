package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.BookService;
import com.example.Library.Management.System.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    private BookService bookService;

    private TransactionService transactionService;

    public BookController(BookService bookService, TransactionService transactionService) {
        super();
        this.bookService = bookService;
        this.transactionService = transactionService;
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
    public String editBookForm(@PathVariable Long id, Model model, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/sign_in";
        }

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

        if (loggedInUser == null) {
            return "redirect:/sign_in";
        }

        if (keyword != null && !keyword.isEmpty()) {
            books = bookService.searchBooks(keyword);
        } else {
            books = bookService.getAllBooks();
        }

        //create a Map that takes bookid and boolean value
        Map<Long, Boolean> canReturnMap = new HashMap<>();

        //looping through all the books
        for(Book book : books) {
            //checking if each of the books can be returned or not
            boolean canReturn = transactionService.isBookBorrowedByUser(book.getId(), loggedInUser.getId());
            //adding the book id inside the map and the boolean value.
            canReturnMap.put(book.getId(), canReturn);
        }

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("librarian", loggedInUser.getRole().equalsIgnoreCase("librarian"));
        model.addAttribute("user", loggedInUser);
        model.addAttribute("canReturnMap", canReturnMap);
        return "books";
    }

    //go to book details page
    @GetMapping("/book/{bookId}")
    public String bookDetails(@PathVariable Long bookId, Model model, HttpSession session) {

        Book book = bookService.getBookById(bookId);

        boolean canReview = false;

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/sign_in";
        } else {
            Long userId = loggedInUser.getId();

//            check if user borrowed and return the book
            canReview = transactionService.hasUserReturnedBook(bookId, userId);
        }

        model.addAttribute("book", book);
        model.addAttribute("canReview", canReview);

        return "book_details";
    }
}

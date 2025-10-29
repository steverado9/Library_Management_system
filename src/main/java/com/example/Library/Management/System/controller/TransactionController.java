package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Transaction;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.repository.TransactionRepository;
import com.example.Library.Management.System.service.BookService;
import com.example.Library.Management.System.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class TransactionController {
    private TransactionService transactionService;

    private BookService bookService;

    private TransactionRepository transactionRepository;

    public TransactionController( TransactionService transactionService, BookService bookService) {
        super();
        this.transactionService = transactionService;
        this.bookService = bookService;
    }

    //Borrow a book
    @PostMapping("/book/{id}/borrow")
    public String borrowBook(@PathVariable Long id, HttpSession session) {
        Book book = bookService.getBookById(id);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        Transaction transaction = new Transaction();
        transaction.setUser(loggedInUser);
        transaction.setBook(book);
        transaction.setBorrowDate(LocalDateTime.now());
        transaction.setDueDate(LocalDateTime.now().plusDays(14));
        transaction.setStatus("BORROWED");

        book.setAvailable(false);

        transactionService.borrowBook(book, transaction);
        return "redirect:/books";
    }

    //Return a book
    @PostMapping("/book/{id}/return")
    public String returnBook(@PathVariable Long id) {
        transactionService.returnBook(id);
        return "redirect:/books";
    }
}

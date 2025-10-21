package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }
}

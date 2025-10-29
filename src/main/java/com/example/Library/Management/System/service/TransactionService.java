package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Transaction;
import com.example.Library.Management.System.entity.User;

public interface TransactionService {
    void borrowBook(Book book, Transaction transaction);

    void returnBook(Long bookId);
}

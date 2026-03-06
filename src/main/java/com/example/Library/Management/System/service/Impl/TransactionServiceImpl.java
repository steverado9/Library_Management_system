package com.example.Library.Management.System.service.Impl;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Transaction;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.TransactionRepository;
import com.example.Library.Management.System.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    public TransactionRepository transactionRepository;

    public BookRepository bookRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BookRepository bookRepository) {
        super();
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void borrowBook(Book book, Transaction transaction) {
        bookRepository.save(book);
        transactionRepository.save(transaction);
    }

    @Override
    public void returnBook(Long bookId) {
        Transaction transaction = transactionRepository.findByBookIdAndStatus(bookId, "BORROWED");

        transaction.setStatus("RETURNED");
        transaction.setReturnDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        Book book = transaction.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
    }

    @Override
    public boolean hasUserReturnedBook(Long bookId, Long userId ) {
        return transactionRepository.existsByBookIdAndUserIdAndStatus(bookId, userId, "RETURNED");
    }
}

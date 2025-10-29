package com.example.Library.Management.System.service.Impl;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.TransactionRepository;
import com.example.Library.Management.System.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private BookRepository  bookRepository;
    private TransactionRepository transactionRepository;

    public BookServiceImpl(BookRepository bookRepository, TransactionRepository transactionRepository) {
        super();
        this.bookRepository = bookRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        this.transactionRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }

}

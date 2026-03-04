package com.example.Library.Management.System.service.Impl;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Review;
import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.ReviewRepository;
import com.example.Library.Management.System.repository.TransactionRepository;
import com.example.Library.Management.System.repository.UserRepository;
import com.example.Library.Management.System.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    public TransactionRepository transactionRepository;

    public UserRepository userRepository;

    public BookRepository bookRepository;

    public ReviewRepository reviewRepository;

    ReviewServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(Long userId, Long bookId, int rating, String comment) {
        //Did the user borrow the book?
        //Is the status RETURNED?
        transactionRepository.findByBookIdAndStatus(bookId, "RETURNED");
    }

    @Override
    public void saveReview(String name, Long bookId, Review review) {

        User user = userRepository.findByName(name);
        Book book = bookRepository.findById(bookId).orElseThrow();

        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }
}

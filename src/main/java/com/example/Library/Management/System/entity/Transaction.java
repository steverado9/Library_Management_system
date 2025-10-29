package com.example.Library.Management.System.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions" )
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //The user who borrowed the book
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //The book being borrowed
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Transaction() {}


    public Transaction(User user, Book book, LocalDateTime borrowDate, LocalDateTime dueDate, String status) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

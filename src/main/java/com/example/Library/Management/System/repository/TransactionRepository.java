package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByBookIdAndStatus(Long bookId, String status);

    void deleteByBookId(Long bookId);

}

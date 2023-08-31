package com.redmath.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM Transaction WHERE account_id = ?", nativeQuery = true)
    Optional<Transaction> findTransactionByAccountId(Long accountId);
    @Query(value = "DELETE FROM Transaction WHERE account_id = ?", nativeQuery = true)
    void deleteByAccountId(Long accountId);
}

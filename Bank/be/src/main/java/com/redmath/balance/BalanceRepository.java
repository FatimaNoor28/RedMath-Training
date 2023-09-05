package com.redmath.balance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance ,Long>{
    @Query(value = "SELECT * FROM Balance WHERE account_id = ?", nativeQuery = true)
    Optional<Balance> findBalanceByAccountId(Long accountId);

    @Query(value = "DELETE FROM Balance WHERE account_id = ?", nativeQuery = true)
    void deleteByAccountId(Long accountId);

}

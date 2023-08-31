package com.redmath.transaction;

import com.redmath.balance.Balance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository){this.repository = repository;}

    public List<Transaction> findAll(){return repository.findAll();}


    public Transaction create(Transaction transaction){return repository.save(transaction); }
    public Optional<Transaction> findTransactionByAccountId(Long accountId){
        Optional<List<Transaction>> balances = Optional.empty();
        return repository.findTransactionByAccountId(accountId);
    }
    public void deleteByAccountId(Long accountId){
        repository.deleteByAccountId(accountId);
    }
}

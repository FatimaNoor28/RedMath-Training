package com.redmath.transaction;

import com.redmath.balance.Balance;
import com.redmath.balance.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final BalanceService balanceService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TransactionService(TransactionRepository repository, BalanceService service){
        this.balanceService = service;
        this.repository = repository;}

    public List<Transaction> findAll(){return repository.findAll();}


    public Transaction MakeTransaction(Transaction transaction){
        Optional<Balance> balance = balanceService.findBalanceByAccountId(transaction.getAccount_id());
        if(balance.isEmpty()) {
            logger.warn("Create balance first");
            return null;
        }
        if(balance.get().getAmount() >= transaction.getAmount() ) {
            balanceService.DeductAmount(transaction.getId() ,transaction.getAmount());
            return repository.save(transaction);
        }


        return repository.save(transaction);
    }
    public Optional<Transaction> findTransactionByAccountId(Long accountId){
        return repository.findTransactionByAccountId(accountId);
    }
    public void deleteByAccountId(Long accountId){
        repository.deleteByAccountId(accountId);
    }
}

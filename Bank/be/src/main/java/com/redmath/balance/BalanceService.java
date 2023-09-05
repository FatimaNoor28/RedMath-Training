package com.redmath.balance;

import com.redmath.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    private final BalanceRepository repository;

    public BalanceService(BalanceRepository repository){this.repository = repository; }

    public List<Balance> findAll(){return repository.findAll();}
    public Optional<Balance> findBalanceByAccountId(Long accountId){
        Optional<List<Balance>> balances = Optional.empty();
        return repository.findBalanceByAccountId(accountId);
    }

    public Balance AddBalance(Balance balance){
        Optional<Balance> oldBalance = repository.findBalanceByAccountId(balance.getAccount_id());
        if(oldBalance.isEmpty())
        {
            System.out.println("service if part called");
            balance.setDate(LocalDate.now());
            return repository.save(balance);
        }
        else{
            System.out.println("service else part called");
            Balance bal = oldBalance.get();
            Long amount = balance.getAmount()+ bal.getAmount();
            bal.setAmount(amount);
            bal.setDB_CR(balance.getDB_CR());
            balance.setDate(LocalDate.now());
            return repository.save(balance);
        }

    }

    public Balance update(Balance balance){return repository.save(balance); }
    public void deleteByAccountId(Long accountId){
        repository.deleteByAccountId(accountId);
    }

    public void DeductAmount(Long id ,Long amount){
        Optional<Balance> balance = repository.findById(id);
        balance.get().setAmount(balance.get().getAmount() - amount);
        repository.save(balance.get());
    }
}

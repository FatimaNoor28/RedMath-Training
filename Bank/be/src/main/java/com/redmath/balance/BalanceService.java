package com.redmath.balance;

import com.redmath.account.AccountRepository;
import org.springframework.stereotype.Service;

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

    public Balance create(Balance balance){return repository.save(balance); }

    public Balance update(Balance balance){return repository.save(balance); }
    public void deleteByAccountId(Long accountId){
        repository.deleteByAccountId(accountId);
    }
}

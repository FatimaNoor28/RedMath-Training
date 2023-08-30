package com.redmath.account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repository;
    public AccountService(AccountRepository repository){ this.repository = repository; }

    public List<Account> findAll(){return repository.findAll();}
    public Optional<Account> findById(Long id){

        return repository.findById(id); }
}

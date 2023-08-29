package com.redmath.account;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/myBank/account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service){ this.service = service; }

    @RequestMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Account>> findAll(){ return ResponseEntity.ok(service.findAll()); }


    @RequestMapping("/{id}")
    @PreAuthorize("hasAuthority('ACCOUNT_HOLDER')")
    public ResponseEntity<Account> findById(@PathVariable("id") Long id){
        Optional<Account> account = service.findById(id);
        if(account.isEmpty()){ResponseEntity.notFound().build(); }
        Account toAccount = account.get();
        return ResponseEntity.ok(toAccount);

    }

}

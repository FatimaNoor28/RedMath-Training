package com.redmath.account;
import com.redmath.balance.Balance;
import com.redmath.basic.ApiResponse;

import com.redmath.transaction.Transaction;

import com.redmath.users.User;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8081/")
public class AccountController {
    @Autowired
    private final AccountService service;

    public AccountController(AccountService service){ this.service = service; }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(String username, String password) {
        // Authenticate user based on username and password
        UserDetails userDetails;
        try {
            userDetails = service.loadUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.ok(false);
        }

        // Perform password validation here (you should implement this logic)
        if (!passwordsMatch(password, userDetails.getPassword())) {
            return ResponseEntity.ok(false);
        }

        // Authentication succeeded
        return ResponseEntity.ok(true);
    }

    private boolean passwordsMatch(String providedPassword, String actualPassword) {
        return providedPassword.equals(actualPassword);
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accounts);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<AccountDetailsResponse>> findById(@PathVariable("id") Long id){

        Optional<AccountDetailsResponse> accountDetailsResponse = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of(accountDetailsResponse.get()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Account>> create(@RequestBody Account account) {
        Account created = service.create(account);
        if (created != null) {
            return ResponseEntity.ok(ApiResponse.of(created));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody Account account){
        service.update(account);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

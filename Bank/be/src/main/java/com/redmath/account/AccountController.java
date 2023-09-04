package com.redmath.account;
import com.redmath.balance.Balance;
import com.redmath.basic.ApiResponse;

import com.redmath.transaction.Transaction;

import com.redmath.users.User;
//import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;




//("http://localhost:8081/")
@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {
    @Autowired
    private final AccountService service;

    public AccountController(AccountService service){ this.service = service; }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody User user){
        LoginMessage loginMessage = service.loginUser(user);
        return ResponseEntity.ok(loginMessage);
    }

    private boolean passwordsMatch(String providedPassword, String actualPassword) {
        return providedPassword.equals(actualPassword);
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accounts);
    }
    @GetMapping("/{id}")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<AccountDetailsResponse>> findById(@PathVariable("id") Long id){

        Optional<AccountDetailsResponse> accountDetailsResponse = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of(accountDetailsResponse.get()));
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Account>> create(@RequestBody Account account) {

        Account created = service.create(account);
        if (created != null) {
            return ResponseEntity.ok(ApiResponse.of(created));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    // @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody Account account){
        service.update(account);
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

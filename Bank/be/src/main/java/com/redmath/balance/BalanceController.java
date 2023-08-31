package com.redmath.balance;

import com.redmath.account.Account;
import com.redmath.account.AccountService;
import com.redmath.basic.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {
    @Autowired
    private final BalanceService service;

    public BalanceController(BalanceService service) {
        this.service = service;
    }


    @PreAuthorize("hasAuthority('ACCOUNT_HOLDER')")
    @PostMapping
    public ResponseEntity<ApiResponse<Balance>> createBalance(@RequestBody Balance account) {
        Balance created = service.create(account);
        if (created != null) {
            return ResponseEntity.ok(ApiResponse.of(created));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PreAuthorize("hasAuthority('ACCOUNT_HOLDER')")
    @PutMapping("/{id}")
    public void update(@RequestBody Balance balance){
        service.update(balance);
    }


}
package com.redmath.transaction;

import com.redmath.balance.Balance;
import com.redmath.balance.BalanceService;
import com.redmath.basic.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }


    @PreAuthorize("hasAuthority('ACCOUNT_HOLDER')")
    @PostMapping
    public ResponseEntity<ApiResponse<Transaction>> MakeTransaction(@RequestBody Transaction transaction) {
        Transaction created = service.MakeTransaction(transaction);
        if (created != null) {
            return ResponseEntity.ok(ApiResponse.of(created));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
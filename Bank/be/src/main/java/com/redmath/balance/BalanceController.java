package com.redmath.balance;

import com.redmath.account.Account;
import com.redmath.account.AccountService;
import com.redmath.basic.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {
    @Autowired
    private final BalanceService service;

    public BalanceController(BalanceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Balance>> findAll() {
        List<Balance> balances = service.findAll();
        if (balances.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(balances);
    }

    @PreAuthorize("hasAuthority('ACCOUNT_HOLDER')")
    @PostMapping()
    public ResponseEntity<ApiResponse<Balance>> createBalance(@RequestBody Balance balance) {
        Balance created = service.createBalance(balance);
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
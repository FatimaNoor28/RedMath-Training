package com.redmath.balance;
//import java.time.LocalDateTime;
import java.util.Date;
import com.redmath.account.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Balance")
public class Balance {
    @Id
    private Long BalanceId;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;
    private Long Amount;
    private String DB_CR;
    private Date Date;

    public Long getId() {
        return BalanceId;
    }

    public void setId(Long BalanceId) {
        this.BalanceId = BalanceId;
    }

    public Long getAmount() {
        return Amount;
    }
    public void setAmount(Long amount) { this.Amount = amount;    }

    public void setDB_CR(String DB_CR) {
        this.DB_CR = DB_CR;
    }

    public String getDB_CR() { return DB_CR;}

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
}

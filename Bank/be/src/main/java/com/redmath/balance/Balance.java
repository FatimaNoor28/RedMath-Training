package com.redmath.balance;
import java.util.Date;
import com.redmath.account.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Balance")
public class Balance {
    @Id
    private Long balance_id;
    private Long account_id;
    private Long amount;
    private String DB_CR;
    private Date date;

    public Long getId() {
        return balance_id;
    }
    public Long getAccount_id() {
        return account_id;
    }
    public void setAccount_id(Long id) {
        this.account_id = id;
    }

    public void setId(Long BalanceId) {
        this.balance_id = BalanceId;
    }

    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) { this.amount = amount;    }

    public void setDB_CR(String DB_CR) {
        this.DB_CR = DB_CR;
    }

    public String getDB_CR() { return DB_CR;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date Date) {
        this.date = Date;
    }
}

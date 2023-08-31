package com.redmath.transaction;
import java.util.Date;
import com.redmath.balance.Balance;
import jakarta.persistence.*;

@Entity(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transaction_id;
    private Long account_id;
    private String description;
    private Long amount;
    private String DB_CR;
    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date transactionDate;

    public Long getId() {
        return transaction_id;
    }

    public void setId(Long id) {
        this.transaction_id = id;
    }
    public Long getBalance_id() {
        return account_id;
    }

    public void setBalance_id(Long account_id) {
        this.account_id = account_id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
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
        return transactionDate;
    }

    public void setDate(Date date) {
        this.transactionDate = date;
    }
}

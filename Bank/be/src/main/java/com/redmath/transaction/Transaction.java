package com.redmath.transaction;
import java.util.Date;
import com.redmath.balance.Balance;
import jakarta.persistence.*;

@Entity(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transaction_id;
//    @ManyToOne
//    @JoinColumn(name = "balance_id", referencedColumnName = "balance_id")
//    private Balance balance;
    @JoinColumn(name = "balance_id", referencedColumnName = "balance_id")
    private Long balance_id;
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
        return balance_id;
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

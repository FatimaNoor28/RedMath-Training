package com.redmath.transaction;
import java.util.Date;
import com.redmath.balance.Balance;
import jakarta.persistence.*;

@Entity(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long TransactionId;
    @ManyToOne
    @JoinColumn(name = "balance_id", referencedColumnName = "balance_id")
    private Balance balance;
    private String description;
    private Long amount;
    @Column(name = "db_cr_indicator")
    private String dbCrIndicator;
    @Temporal(TemporalType.DATE)
    @Column(name = "transaction_date")
    private Date transactionDate;

    public Long getId() {
        return TransactionId;
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
        this.dbCrIndicator = DB_CR;
    }

    public String getDB_CR() { return dbCrIndicator;}

    public Date getDate() {
        return transactionDate;
    }

    public void setDate(Date date) {
        this.transactionDate = date;
    }
}

package com.redmath.account;
import com.redmath.balance.Balance;
import com.redmath.balance.BalanceService;
import com.redmath.transaction.Transaction;
import com.redmath.transaction.TransactionService;
import com.redmath.users.User;
import com.redmath.users.UserService;

import org.springframework.security.core.Authentication;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Value("${account.page.min:0}")
//    public int pageMin = 0;
//
//    @Value("${account.page.size.min:1}")
//    public int pageSizeMin = 1;
//
//    @Value("${account.page.size.max:100}")
//    public int pageSizeMax = 100;
    private final AccountRepository repository;
    private final UserService userService;
    private final BalanceService balanceService;
    private final TransactionService transactionService;
    public AccountService(AccountRepository repository, UserService userService, BalanceService balanceService, TransactionService transactionService)
    {
        this.repository = repository;
        this.userService = userService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }

    public List<Object> findAll(){
        List<Account> accounts = repository.findAll();
        List<Balance> balance = balanceService.findAll();
        List<Transaction> transaction = transactionService.findAll();

        List<Object> list = new ArrayList<>();
        list.add(accounts);
        list.add(balance);
        list.add(transaction);

        return list;
    }

    public Optional<AccountDetailsResponse> findById(Long id){

        Optional<Account> account= repository.findById(id);
        if (account.isEmpty()) {
            return Optional.empty();
        }
            Optional<Balance> balance = balanceService.findBalanceByAccountId(account.get().getId());
            Optional<Transaction> transaction = transactionService.findTransactionByAccountId(account.get().getId());

        AccountDetailsResponse response = new AccountDetailsResponse();

        response.setAccount(account.get());
        response.setBalance(balance);
        response.setTransaction(transaction);
        logger.warn("Not Authorized!!");
        return Optional.of(response);
    }

    public Account create(Account account){
        if (account.getId() != null && repository.existsById(account.getId())) {
            logger.warn("News with id {} already exists", account.getId());
            return null;
        }
        return repository.save(account);
    }

    public Optional<Void> update(Account account){
        if(account.getId()!=null && repository.existsById(account.getId())){
            repository.save(account);
            return Optional.empty();
        }
        return null;
    }

    public Optional<Void> delete(Long id){
        if (repository.existsById(id)) {
            Optional<Account> accountToBeDeleted = repository.findById(id);
            balanceService.deleteByAccountId(id);
            transactionService.deleteByAccountId(id);
            repository.delete(accountToBeDeleted.get());

            return Optional.empty();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByUserName(name);
        Account account = repository.findByName(name);
        if (account == null) {
            throw new UsernameNotFoundException("Invalid user: " + name);
        }
        return new org.springframework.security.core.userdetails.User(account.getName(), account.getPassword(), true,
                true, true,true, AuthorityUtils.commaSeparatedStringToAuthorityList(account.getRoles()));
    }

}

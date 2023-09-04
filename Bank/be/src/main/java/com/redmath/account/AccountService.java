package com.redmath.account;
import com.redmath.balance.Balance;
import com.redmath.balance.BalanceService;
import com.redmath.transaction.Transaction;
import com.redmath.transaction.TransactionService;
import com.redmath.users.User;
import com.redmath.account.LoginMessage;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import com.redmath.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AccountService  { //implements UserDetailsService

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AccountRepository repository;
    private final UserService userService;
    private final BalanceService balanceService;
    private final TransactionService transactionService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AccountService(AccountRepository repository, UserService userService, BalanceService balanceService, TransactionService transactionService)
    {
        this.repository = repository;
        this.userService = userService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }


    public LoginMessage loginUser(User user){
        String message="";
        User user1 = userService.findByUserName(user.getUserName());
        if(user1!=null) {
            String password = user.getPassword();
            String encodedPassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            Boolean isPwdRight = password.equals(encodedPassword);
            if (isPwdRight && user1.getRoles().equals("ACCOUNT_HOLDER")) {
                Optional<Account> account = repository.findAccountByNameAndPassword(user.getUserName(), encodedPassword);
                if (account.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else if (isPwdRight && user1.getRoles().equals("ADMIN")) {
                return new LoginMessage("Login Success", true);
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("User Name not exits", false);
        }
    }

    public List<Account> findAll(){
        return repository.findAll();
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
        passwordEncoder.encode(account.getPassword());
        userService.create(account.getName(), account.getPassword());
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

    // @Override
    // public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    //     User user = userService.findByUserName(name);
    //     //Account account = repository.findByName(name);
    //     if (user == null) {
    //         throw new UsernameNotFoundException("Invalid user: " + name);
    //     }
    //     return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true,
    //             true, true,true, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
    // }

}

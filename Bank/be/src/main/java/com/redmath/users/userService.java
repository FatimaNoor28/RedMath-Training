package com.redmath.users;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){this.repository = repository; }

    public User findByUserName(String user_name){
        return repository.findByUserName(user_name);
    }
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }
}
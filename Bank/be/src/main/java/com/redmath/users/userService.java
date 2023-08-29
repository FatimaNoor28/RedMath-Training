package com.redmath.users;


import org.springframework.stereotype.Service;

@Service
public class userService {
    private final userRepository repository;

    public userService(userRepository repository){this.repository = repository; }

    public User findByUserName(String user_name){
        return repository.findByUserName(user_name);
    }
}
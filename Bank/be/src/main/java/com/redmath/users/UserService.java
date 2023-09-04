package com.redmath.users;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){this.repository = repository; }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findByUserName(String user_name){
        return repository.findByUserName(user_name);
    }
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public User create(String name, String password){
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setRoles("ADMIN");
        return repository.save(user);
    }
}
package com.wealthgame.backend.ioc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component

public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository)
    {
        this.repository = repository;
        System.out.println("Constructor initialized");
    }
    @PostConstruct
    public void init()
    {
        System.out.println("UserService Initialized");
    }
    @PreDestroy
    public void destroy()
    {
        System.out.println("UserService Destroyed");
    }

    public String createUser()
    {
        repository.saveUser();
        return "Done";
    }


}

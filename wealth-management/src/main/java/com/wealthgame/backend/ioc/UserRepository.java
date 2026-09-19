package com.wealthgame.backend.ioc;

import org.springframework.stereotype.Component;


public class UserRepository {
    public void saveUser()
    {
        System.out.println("User saved");
    }
}

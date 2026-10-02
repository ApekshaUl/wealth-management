package com.wealthgame.backend.repository;

import com.wealthgame.backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Long, User> users= new HashMap<>();
    public User save(User user)
    {
        users.put(user.getId(),user);
        return user;
    }
    public User findById(Long id)
    {
        return users.get(id);
    }
    public Map<Long,User> findAll()
    {
        return users;
    }
    public User update(User user)
    {
        users.put(user.getId(),user);
        return user;
    }
    public void deleteUser(Long id)
    {
        users.remove(id);
    }
}

package com.wealthgame.backend.service;

import com.wealthgame.backend.dto.UserCreateRequestDTO;
import com.wealthgame.backend.dto.UserResponseDTO;
import com.wealthgame.backend.repository.UserRepository;
import com.wealthgame.backend.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AtomicLong idGenerator = new AtomicLong(0);
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public UserResponseDTO createUser(UserCreateRequestDTO request)
    {
        Long id = idGenerator.incrementAndGet();
        User user = new User(
                id,
                request.getName(),
                request.getEmail()
        );
        userRepository.save(user);
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail()
        );
    }
    public UserResponseDTO getUserById(Long id)
    {
        User user = userRepository.findById(id);
        if(user==null)
        {
            return null;
        }
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail()
        );

    }
    public List<UserResponseDTO> getAllUsers()
    {
        return userRepository.findAll().values().stream().map(
                user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                )
        ).toList();
    }
    public UserResponseDTO update(Long id, UserCreateRequestDTO request)
    {
        User existingUser = userRepository.findById(id);
        if(existingUser==null)
        {
            return null;
        }
        User updatedUser = new User(
                id,
                request.getName(),
                request.getEmail()
        );
        userRepository.update(updatedUser);
        return new UserResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getName());
    }
    public void deleteUser(Long id)
    {
        User user = userRepository.findById(id);
        if(user==null)
        {
            return;
        }
        userRepository.deleteUser(id);
    }

}

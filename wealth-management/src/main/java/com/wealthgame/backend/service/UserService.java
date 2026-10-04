package com.wealthgame.backend.service;

import com.wealthgame.backend.dto.PageResponseDTO;
import com.wealthgame.backend.dto.UserCreateRequestDTO;
import com.wealthgame.backend.dto.UserResponseDTO;
import com.wealthgame.backend.exception.UserNotFoundException;
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
            throw new UserNotFoundException("User Not Found "+id);
        }
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail()
        );

    }
    public PageResponseDTO<UserResponseDTO> getAllUsers(int page, int size)
    {

        List<User> users = userRepository.findAll().values().stream().toList();
        int totalElements = users.size();
        int startIndex = page * size;
        if(startIndex >= totalElements)
        {
            return new PageResponseDTO<>(
                    List.of(),
                    page,
                    size,
                    totalElements,
                    (int) Math.ceil((double) totalElements/size)
            );
        }
        int endIndex = Math.min(startIndex + size,totalElements);
        List<UserResponseDTO> content = users.subList(startIndex,endIndex).stream().map(user-> new UserResponseDTO(
                user.getId(),user.getName(),user.getEmail()
                )
        ).toList();
        int totalPages = (int) Math.ceil((double)totalElements/size);
        return new PageResponseDTO<>(content,page,size,totalElements,totalPages);
    }
    public UserResponseDTO update(Long id, UserCreateRequestDTO request)
    {
        User existingUser = userRepository.findById(id);
        if(existingUser==null)
        {
            throw new UserNotFoundException("User Not Found "+id);
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
            throw new UserNotFoundException("User Not Found "+id);
        }
        userRepository.deleteUser(id);
    }

}

package com.wealthgame.backend.controller;

import com.wealthgame.backend.dto.PageResponseDTO;
import com.wealthgame.backend.dto.UserCreateRequestDTO;
import com.wealthgame.backend.dto.UserResponseDTO;
import com.wealthgame.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(
        name = "Users",
        description="API for managing users"
)
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateRequestDTO request)
    {
        UserResponseDTO response = userService.createUser(request);
        return ResponseEntity.status(201).body(response);
    }
    @Operation(
            summary = "get users by id",
            description = "Retrives user with unique IDs"

    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserId(@PathVariable Long id)
    {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<PageResponseDTO<UserResponseDTO>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
            )
    {
        PageResponseDTO<UserResponseDTO> response = userService.getAllUsers(name,page,size,sortBy,direction);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Parameter(
            description = "Unique ID of the user",
            example = "1"
    )@PathVariable Long id, @Valid @RequestBody UserCreateRequestDTO request)
    {
        UserResponseDTO response= userService.update(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

package me.prabh.journal.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.prabh.journal.DTO.creationDTO.UserCreateDTO;
import me.prabh.journal.DTO.responseDTO.UserResponseDTO;
import me.prabh.journal.DTO.updationDTO.UserUpdateDTO;
import me.prabh.journal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    //create user
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){
        UserResponseDTO createdUser = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //update user.
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserUpdateDTO updateDTO){
        return ResponseEntity.ok(userService.updateUser(id,updateDTO));
    }

    //delete user by id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
}

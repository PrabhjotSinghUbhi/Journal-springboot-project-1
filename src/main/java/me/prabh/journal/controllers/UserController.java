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

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateDTO updateDTO){
        return ResponseEntity.ok(userService.updateUser(updateDTO));
    }

    //delete user by id.
    @DeleteMapping
    public ResponseEntity<Boolean> deleteUserById(){
        return ResponseEntity.ok(userService.deleteUserById());
    }
}

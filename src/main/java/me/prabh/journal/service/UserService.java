package me.prabh.journal.service;

import me.prabh.journal.DTO.creationDTO.UserCreateDTO;
import me.prabh.journal.DTO.responseDTO.UserResponseDTO;
import me.prabh.journal.DTO.updationDTO.UserUpdateDTO;
import me.prabh.journal.entity.User;
import me.prabh.journal.exceptions.ResourceNotFoundException;
import me.prabh.journal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create user
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        User newUser = new User();
        newUser.setUsername(userCreateDTO.username());
        newUser.setPassword(userCreateDTO.password());
        newUser.setEmail(userCreateDTO.email());

        User user = userRepository.save(newUser);
        return UserResponseDTO.fromEntity(user);
    }

    //get users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponseDTO::fromEntity)
                .toList();
    }

    //get user by id
    public UserResponseDTO getUserById(String id) {
        return userRepository
                .findById(id)
                .map(UserResponseDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("User now found."));
    }

    //update user
    public UserResponseDTO updateUser(String id, UserUpdateDTO updateDTO) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exists"));

        user.setUsername(updateDTO.username());
        user.setEmail(updateDTO.email());
        user.setPassword(updateDTO.password());

        User updatedUser = userRepository.save(user);
        return UserResponseDTO.fromEntity(updatedUser);
    }

    //delete user by id
    public boolean deleteUserById(String id){
        boolean user = userRepository.existsById(id);
        if(!user) throw new ResourceNotFoundException("User does not exists");

        userRepository.deleteById(id);
        return true;
    }

}

package me.prabh.journal.service;

import me.prabh.journal.DTO.creationDTO.UserCreateDTO;
import me.prabh.journal.DTO.responseDTO.UserResponseDTO;
import me.prabh.journal.DTO.updationDTO.UserUpdateDTO;
import me.prabh.journal.entity.User;
import me.prabh.journal.exceptions.ResourceNotFoundException;
import me.prabh.journal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //create user
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        User newUser = new User();
        newUser.setUsername(userCreateDTO.username());
        newUser.setPassword(
                passwordEncoder.encode(userCreateDTO.password())
        );
        newUser.setEmail(userCreateDTO.email());
        newUser.setRoles(List.of("USER"));

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
    public UserResponseDTO updateUser(UserUpdateDTO updateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        if (updateDTO.username() != null && !updateDTO.username().isBlank()) {
            user.setUsername(updateDTO.username());
        }

        if (updateDTO.email() != null && !updateDTO.email().isBlank()) {
            user.setEmail(updateDTO.email());
        }

        if (updateDTO.password() != null && !updateDTO.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateDTO.password()));

            System.out.println(
                    passwordEncoder.matches(
                            updateDTO.password(),
                            user.getPassword()
                    )
            );
        }

        User updatedUser = userRepository.save(user);
        return UserResponseDTO.fromEntity(updatedUser);
    }

    //delete user by id
    public boolean deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        String username = authentication.getName();

        var val = authentication.getCredentials();
        System.out.println(val);

        User user = userRepository.findByUsername(username);
        if(user == null) throw new ResourceNotFoundException("User does not exists");

        userRepository.deleteById(user.getId());
        return true;
    }

}

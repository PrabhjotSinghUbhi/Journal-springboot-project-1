package me.prabh.journal.DTO.updationDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(min = 8, message = "Username should be of at least 8 characters")
        @Size(max = 30, message = "Username should not exceed 30 characters")
        @NotBlank(message = "username is required")
        String username,

        @NotNull(message = "password is required.")
        @Size(min = 8, message = "Password should be of at least 8 characters")
        String password,

        @NotBlank(message = "Email is required.")
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is not valid.")
        String email
) {
}

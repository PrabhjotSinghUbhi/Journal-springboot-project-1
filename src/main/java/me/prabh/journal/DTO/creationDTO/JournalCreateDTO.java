package me.prabh.journal.DTO.creationDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JournalCreateDTO(
        @NotBlank(message = "Title cannot be empty")
        @Size(max = 100, message = "Title must be less than 100 characters.")
        String title,

        @NotBlank(message = "Content cannot be empty")
        String content
) {
}

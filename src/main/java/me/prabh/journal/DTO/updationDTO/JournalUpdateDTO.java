package me.prabh.journal.DTO.updationDTO;

import jakarta.validation.constraints.Size;

public record JournalUpdateDTO(

        @Size(min = 1, message = "Title cannot be empty")
        String title,

        @Size(min = 1, message = "Content cannot be empty")
        String content

) {
}

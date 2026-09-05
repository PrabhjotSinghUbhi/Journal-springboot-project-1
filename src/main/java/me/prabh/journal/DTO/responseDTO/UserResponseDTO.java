package me.prabh.journal.DTO.responseDTO;

import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(
        String id,
        String username,
        String password,
        List<JournalEntry> journalEntries,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String email) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getJournalEntries(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getEmail()
        );
    }
}

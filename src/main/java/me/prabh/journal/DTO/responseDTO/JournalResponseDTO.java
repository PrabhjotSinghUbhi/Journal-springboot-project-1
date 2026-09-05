package me.prabh.journal.DTO.responseDTO;

import me.prabh.journal.entity.JournalEntry;

import java.time.LocalDateTime;

public record JournalResponseDTO(

        String id,
        String title,
        String content,
        LocalDateTime createdAt
) {
    public static JournalResponseDTO fromEntity(JournalEntry entry) {
        return new JournalResponseDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getContent(),
                entry.getCreatedAt()
        );
    }
}
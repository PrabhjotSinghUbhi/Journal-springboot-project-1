package me.prabh.journal.service;

import lombok.RequiredArgsConstructor;
import me.prabh.journal.DTO.creationDTO.JournalCreateDTO;
import me.prabh.journal.DTO.responseDTO.JournalResponseDTO;
import me.prabh.journal.DTO.updationDTO.JournalUpdateDTO;
import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.entity.User;
import me.prabh.journal.exceptions.AccessDeniedException;
import me.prabh.journal.exceptions.ResourceNotFoundException;
import me.prabh.journal.repository.JournalEntryRepository;
import me.prabh.journal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserRepository userRepository;

    //save Entry
    @Transactional
    public JournalResponseDTO saveEntry(JournalCreateDTO entry) {
        //create entity.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("User not found");

        JournalEntry journalEntry = new JournalEntry();

        //set the values.
        journalEntry.setTitle(entry.title());
        journalEntry.setContent(entry.content());

        //save the response.
        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);

        user.getJournalEntries().add(savedEntry);
        userRepository.save(user);

        //send the response.
        return JournalResponseDTO.fromEntity(savedEntry);
    }

    //get all entries
    public List<JournalResponseDTO> getAllEntries() {
        List<JournalEntry> allEntries = journalEntryRepository.findAll();

        return allEntries
                .stream()
                .map(JournalResponseDTO::fromEntity)
                .toList();
    }

    //get entries by id
    public JournalResponseDTO getEntryById(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated"); // Or a custom exception
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        boolean containsEntryInUserEntries = user.getJournalEntries().stream().anyMatch(x -> x.getId().equals(id));

        if (!containsEntryInUserEntries) {
            throw new ResourceNotFoundException("Not such entry exists in users journal entries.");
        }

        return journalEntryRepository
                .findById(id)
                .map(JournalResponseDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Not such entry exists."));
    }

    //check if the journal exists.
    public boolean entryExists(String id) {
        return journalEntryRepository.existsById(id);
    }

    //count the number of entries
    public long countEntries() {
        return journalEntryRepository.count();
    }

    //delete entries by id
    public boolean deleteEntryById(String id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated"); // Or a custom exception
        }

        String username = authentication.getName();

        if (!journalEntryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not Such entry exists");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("User does not exists");

        boolean containsEntryInUserEntries = user.getJournalEntries().stream().anyMatch(x -> x.getId().equals(id));

        if (!containsEntryInUserEntries) {
            throw new ResourceNotFoundException("Not such entry exists in users journal entries.");
        }

        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userRepository.save(user);

        journalEntryRepository.deleteById(id);
        return true;
    }

    public List<JournalResponseDTO> getAllEntriesOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User is not authenticated"); // Or a custom exception
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        if (user == null) throw new ResourceNotFoundException("User not found");

        return user.getJournalEntries().stream().map(JournalResponseDTO::fromEntity).toList();
    }

    //edit title and content.
    public JournalResponseDTO editEntry(String id, JournalUpdateDTO updateDTO) {

        //find by id
        JournalEntry entry = journalEntryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The entry to be updated does not exists"));

        entry.setTitle(updateDTO.title());
        entry.setContent(updateDTO.content());
        JournalEntry updatedEntry = journalEntryRepository.save(entry);

        return JournalResponseDTO.fromEntity(updatedEntry);
    }


    //pagination - findAll - sorted.
    //pagination - findAll - pageable.

}

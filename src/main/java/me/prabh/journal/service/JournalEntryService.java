package me.prabh.journal.service;

import me.prabh.journal.DTO.JournalCreateDTO;
import me.prabh.journal.DTO.JournalResponseDTO;
import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.exceptions.ResourceNotFoundException;
import me.prabh.journal.repository.JournalEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    //save Entry
    public JournalResponseDTO saveEntry(JournalCreateDTO entry) {
        //create entity.
        JournalEntry journalEntry = new JournalEntry();

        //set the values.
        journalEntry.setTitle(entry.title());
        journalEntry.setContent(entry.content());

        //save the response.
        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);

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

    //delete all entries
    public boolean deleteAllEntries() {
        journalEntryRepository.deleteAll();
        return true;
    }

    //delete entries by id
    public boolean deleteEntryById(String id) {
        journalEntryRepository.deleteById(id);
        return true;
    }

    //edit title
    public JournalResponseDTO editEntry(String id, String newTitle, String newContent) {
        try {
            //find by id
            Optional<JournalEntry> entryToBeUpdated = journalEntryRepository.findById(id);

            entryToBeUpdated.ifPresent(entry -> {
                //update the title.
                entry.setTitle(newTitle);
                entry.setContent(newContent);
                journalEntryRepository.save(entry);
            });

            return entryToBeUpdated
                    .map(JournalResponseDTO::fromEntity)
                    .orElseThrow(RuntimeException::new);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //pagination - findAll - sorted.
    //pagination - findAll - pageable.

}

package me.prabh.journal.service;
import me.prabh.journal.entity.JournalEntry;
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
    public JournalEntry saveEntry(JournalEntry entry){
        try{
            return journalEntryRepository.save(entry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get all entries
    public List<JournalEntry> getAllEntries(){
        try{
            return journalEntryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get entries by id
    public Optional<JournalEntry> getEntryById(String id){
        try{
            return journalEntryRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //check if the journal exists.
    public boolean entryExists(String id){
        try{
            return journalEntryRepository.existsById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //count the number of entries
    public long countEntries(){
        try{
            return journalEntryRepository.count();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //delete all entries
    public boolean deleteAllEntries() {
        try {
            journalEntryRepository.deleteAll();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //delete entries by id
    public boolean deleteEntryById(String id) {
        try {
            journalEntryRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //edit title
    public JournalEntry editEntry(String id, String newTitle, String newContent) {
        try{
            //find by id
            Optional<JournalEntry> entryToBeUpdated = journalEntryRepository.findById(id);
            entryToBeUpdated.ifPresent(entry -> {
                //update the title.
                entry.setTitle(newTitle);
                entry.setContent(newContent);
                journalEntryRepository.save(entry);
            });
            return entryToBeUpdated.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //pagination - findAll - sorted.
    //pagination - findAll - pageable.

}

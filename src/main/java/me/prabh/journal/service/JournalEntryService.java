package me.prabh.journal.service;
import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public JournalEntry saveEntry(JournalEntry entry){
        try{
            return journalEntryRepository.save(entry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

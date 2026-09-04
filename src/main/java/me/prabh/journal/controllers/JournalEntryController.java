package me.prabh.journal.controllers;

import me.prabh.journal.DTO.JournalUpdateDTO;
import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    //get the service
    @Autowired
    JournalEntryService journalEntryService;

    //save entry
    @PostMapping
    public JournalEntry postEntry(@RequestBody JournalEntry entry) {
        return journalEntryService.saveEntry(entry);
    }

    //get all entries
    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return journalEntryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry getEntryById(@PathVariable String id) {
        return journalEntryService.getEntryById(id).get();
    }

    @GetMapping("/exists/{id}")
    public boolean entryExists(@PathVariable String id) {
        return journalEntryService.entryExists(id);
    }

    @GetMapping("/count")
    public long countEntries(){
        return journalEntryService.countEntries();
    }

    @DeleteMapping
    public boolean deleteAllEntries(){
        return journalEntryService.deleteAllEntries();
    }

    @DeleteMapping("/{id}")
    public boolean deleteEntryById(@PathVariable String id) {
        return journalEntryService.deleteAllEntries();
    }

    @PatchMapping("/{id}")
    public JournalEntry udpateEntry(@PathVariable String id, @RequestBody JournalUpdateDTO dto) {
        return journalEntryService.editEntry(id, dto.getTitle(), dto.getContent());
    }


}

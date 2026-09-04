package me.prabh.journal.controllers;

import org.springframework.web.bind.annotation.*;

//DEMO CODE without Database CRUD.
@RestController
@RequestMapping("/_journal")
public class _JournalEntryController {
//
//    //create a simple Map that contains the Key, JournalEntry
//    Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    //get-all
//    @GetMapping
//    public Collection<JournalEntry> getAll() {
//        return journalEntries.values();
//    }
//
//    //create-entry
//    @PostMapping
//    public boolean postEntry(@RequestBody JournalEntry entry) {
//        journalEntries.put(entry.getId(), entry);
//        return true;
//    }
//
//    //get entry by id
//    @GetMapping("/{id}")
//    public JournalEntry getEntryById(@PathVariable Long id) {
//        return journalEntries.get(id);
//    }
//
//    //delete entry
//    @DeleteMapping("/{id}")
//    public boolean deleteEntryById(@PathVariable Long id) {
//        journalEntries.remove(id);
//        return true;
//    }
//
//    //update entry
//    @PatchMapping("/{id}")
//    public boolean updateEntryById(@PathVariable Long id, @RequestBody JournalEntry updatedEntry) {
//        journalEntries.put(id, updatedEntry);
//        return true;
//    }

}

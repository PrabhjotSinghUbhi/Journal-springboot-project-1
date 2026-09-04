package me.prabh.journal.controllers;

import me.prabh.journal.entity.JournalEntry;
import me.prabh.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    //get the service
    @Autowired
    JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry postEntry(@RequestBody JournalEntry entry){
        try {
            return journalEntryService.saveEntry(entry);
        }catch(Exception e) {
            return null;
        }
    }

}

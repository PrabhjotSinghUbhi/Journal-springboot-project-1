package me.prabh.journal.controllers;

import jakarta.validation.Valid;
import me.prabh.journal.DTO.creationDTO.JournalCreateDTO;
import me.prabh.journal.DTO.responseDTO.JournalResponseDTO;
import me.prabh.journal.DTO.updationDTO.JournalUpdateDTO;
import me.prabh.journal.service.JournalEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    //get the service
    final JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    //save entry
    @PostMapping
    public ResponseEntity<JournalResponseDTO> postEntry(@Valid @RequestBody JournalCreateDTO entry) {
        JournalResponseDTO res = journalEntryService.saveEntry(entry);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }

    //get all entries
    @GetMapping
    public ResponseEntity<List<JournalResponseDTO>> getAllEntries() {
        List<JournalResponseDTO> res = journalEntryService.getAllEntries();
        return ResponseEntity
                .ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalResponseDTO> getEntryById(@PathVariable String id) {
        JournalResponseDTO res = journalEntryService.getEntryById(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<?> entryExists(@PathVariable String id) {
        boolean res = journalEntryService.entryExists(id);
        if (res) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @GetMapping("/count")
    public long countEntries() {
        return journalEntryService.countEntries();
    }

    @DeleteMapping
    public boolean deleteAllEntries() {
        return journalEntryService.deleteAllEntries();
    }

    @DeleteMapping("/{id}")
    public boolean deleteEntryById(@PathVariable String id) {
        return journalEntryService.deleteEntryById(id);
    }

    @PatchMapping("/{id}")
    public JournalResponseDTO updateEntry(@PathVariable String id, @RequestBody JournalUpdateDTO dto) {
        return journalEntryService.editEntry(id, dto);
    }


}

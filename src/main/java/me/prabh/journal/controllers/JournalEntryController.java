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

    @GetMapping
    public ResponseEntity<List<JournalResponseDTO>> getAllEntriesOfUser() {
        List<JournalResponseDTO> res = journalEntryService.getAllEntriesOfUser();
        return ResponseEntity
                .ok(res);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEntryById(@PathVariable String id) {
        return journalEntryService.deleteEntryById(id);
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

    @PatchMapping("/{id}")
    public JournalResponseDTO updateEntry(@PathVariable String id,@Valid @RequestBody JournalUpdateDTO dto) {
        return journalEntryService.editEntry(id, dto);
    }


}

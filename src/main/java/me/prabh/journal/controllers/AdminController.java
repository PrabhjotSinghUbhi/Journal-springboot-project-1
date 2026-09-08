package me.prabh.journal.controllers;

import me.prabh.journal.DTO.responseDTO.JournalResponseDTO;
import me.prabh.journal.DTO.responseDTO.UserResponseDTO;
import me.prabh.journal.service.JournalEntryService;
import me.prabh.journal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    final JournalEntryService journalEntryService;
    final UserService userService;

    public AdminController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping("/journals")
    public ResponseEntity<List<JournalResponseDTO>> getAllEntries() {
        List<JournalResponseDTO> res = journalEntryService.getAllEntries();
        return ResponseEntity
                .ok(res);
    }

    @GetMapping("/journal/{id}")
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

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @GetMapping("/count")
    public long countEntries() {
        return journalEntryService.countEntries();
    }

}

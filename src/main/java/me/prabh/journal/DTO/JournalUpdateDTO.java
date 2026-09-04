package me.prabh.journal.DTO;

import jakarta.validation.constraints.Size;

public class JournalUpdateDTO {

    @Size(min = 1, message = "Title cannot be empty")
    private String title;

    @Size(min = 1, message = "Content cannot be empty")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

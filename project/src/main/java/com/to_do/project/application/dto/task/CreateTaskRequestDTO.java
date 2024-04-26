package com.to_do.project.application.dto.task;

import java.util.Date;
import java.util.UUID;

public record CreateTaskRequestDTO(String title, String description, Date dueDate, String priority, UUID userId) {

    public CreateTaskRequestDTO {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("End date cannot be null or empty");
        }
        if (priority == null || priority.isBlank()) {
            throw new IllegalArgumentException("Priority cannot be null or empty");
        }
    }
}

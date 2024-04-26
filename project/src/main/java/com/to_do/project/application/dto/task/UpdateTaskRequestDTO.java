package com.to_do.project.application.dto.task;

import java.util.Date;

public record UpdateTaskRequestDTO(String title, String description, Date dueDate, String priority, boolean done, String userId, boolean deleted) {
    public UpdateTaskRequestDTO {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Start date cannot be null or empty");
        }
        if (priority == null || priority.isBlank()) {
            throw new IllegalArgumentException("Priority cannot be null or empty");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User id cannot be null or empty");
        }
    }
}

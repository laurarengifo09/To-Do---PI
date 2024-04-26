package com.to_do.project.application.dto.task;

import com.to_do.project.domain.entities.task.Task;

import java.util.Date;
import java.util.List;

public record TaskResponseDTO(String id, String title, String description, Date dueDate, String priority, boolean done, Date createdAt, Date updatedAt) {
    public static TaskResponseDTO from(Task task) {
        return new TaskResponseDTO(
                task.getId().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.isDone(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static List<TaskResponseDTO> fromList(List<Task> tasks) {
        return tasks.stream().map(task -> new TaskResponseDTO(
                task.getId().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.isDone(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        )).toList();
    }
}

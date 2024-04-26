package com.to_do.project.application.services.task;

import com.to_do.project.application.dto.common.PaginationDTO;
import com.to_do.project.application.dto.task.CreateTaskRequestDTO;
import com.to_do.project.application.dto.task.FiltersDTO;
import com.to_do.project.application.dto.task.TaskResponseDTO;
import com.to_do.project.application.dto.task.UpdateTaskRequestDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponseDTO createTask(CreateTaskRequestDTO createTaskRequestDTO);

    List<TaskResponseDTO> getTasksByUserId(UUID userId, FiltersDTO filtersDTO, String sorting, PaginationDTO paginationDTO);

    TaskResponseDTO getTaskById(UUID taskId);

    TaskResponseDTO updateTask(UUID taskId, UpdateTaskRequestDTO updateTaskRequestDTO);

    boolean deleteTaskById(UUID taskId);
}

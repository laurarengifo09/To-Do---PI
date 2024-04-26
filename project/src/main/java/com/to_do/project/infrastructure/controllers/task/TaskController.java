package com.to_do.project.infrastructure.controllers.task;

import com.to_do.project.application.dto.common.PaginationDTO;
import com.to_do.project.application.dto.task.*;
import com.to_do.project.application.services.auth.AuthService;
import com.to_do.project.application.services.task.TaskService;
import jakarta.annotation.Nullable;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @GetMapping(value = "/home")
    public String welcome() {
        return "Welcome to the Task Manager!";
    }

    private final TaskService _taskService;

    TaskController(TaskService taskService) {
        this._taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        return ResponseEntity.ok(_taskService.createTask(createTaskRequestDTO));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable UUID userId, @RequestParam(required = false) String content, @RequestParam(required = false) String startDate,
                                                                  @RequestParam(required = false) String endDate, @RequestParam(required = false) String priority, @RequestParam(required = false) Boolean done,
                                                                  @RequestParam(required = false) Boolean deleted,
                                                                  @RequestParam(required = false) String sorting, @RequestParam(required = false) int page, @RequestParam(required = false) int size) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);


        FiltersDTO filters = new FiltersDTO(content, startDate != null ? formatter.parse(startDate) : null, endDate != null ? formatter.parse(endDate) : null, priority, done, deleted);
        PaginationDTO pagination = new PaginationDTO(page, size);

        return ResponseEntity.ok(_taskService.getTasksByUserId(userId, filters, sorting, pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(_taskService.getTaskById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequestDTO updateTaskRequestDTO) {
        return ResponseEntity.ok(_taskService.updateTask(id, updateTaskRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable UUID id) {
        return ResponseEntity.ok(_taskService.deleteTaskById(id));
    }
}

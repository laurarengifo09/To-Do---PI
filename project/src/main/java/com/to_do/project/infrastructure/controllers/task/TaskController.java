package com.to_do.project.infrastructure.controllers.task;

import com.to_do.project.application.dto.common.PaginationDTO;
import com.to_do.project.application.dto.task.*;
import com.to_do.project.application.services.auth.AuthService;
import com.to_do.project.application.services.task.TaskService;
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
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable UUID userId, @RequestParam String content, @RequestParam String startDate,
                                                                  @RequestParam String endDate, @RequestParam String priority, @RequestParam Boolean done,
                                                                  @RequestParam Boolean deleted,
                                                                  @RequestParam String sorting, @RequestParam int page, @RequestParam int size) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);


        FiltersDTO filters = new FiltersDTO(content, formatter.parse(startDate), formatter.parse(endDate), priority, done, deleted);
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

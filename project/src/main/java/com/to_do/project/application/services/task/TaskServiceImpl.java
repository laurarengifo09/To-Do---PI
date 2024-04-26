package com.to_do.project.application.services.task;

import com.to_do.project.application.dto.common.PaginationDTO;
import com.to_do.project.application.dto.task.CreateTaskRequestDTO;
import com.to_do.project.application.dto.task.FiltersDTO;
import com.to_do.project.application.dto.task.TaskResponseDTO;
import com.to_do.project.application.dto.task.UpdateTaskRequestDTO;
import com.to_do.project.domain.entities.task.Task;
import com.to_do.project.domain.repositories.TaskRepository;
import com.to_do.project.domain.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository _taskRepository;
    private final UserRepository _userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this._taskRepository = taskRepository;
        this._userRepository = userRepository;
    }

    @Override
    public TaskResponseDTO createTask(CreateTaskRequestDTO createTaskRequestDTO) {
        var user = _userRepository.findById(createTaskRequestDTO.userId()).orElseThrow();

        var task = _taskRepository.save(new Task(
                createTaskRequestDTO.title(),
                createTaskRequestDTO.description(),
                createTaskRequestDTO.dueDate(),
                createTaskRequestDTO.priority(),
                user
        ));

        return TaskResponseDTO.from(task);
    }

    @Override
    public List<TaskResponseDTO> getTasksByUserId(UUID userId, FiltersDTO filtersDTO, String sorting, PaginationDTO paginationDTO) {

        var pagination = PageRequest.of(paginationDTO.page(), paginationDTO.size(), Sort.by(Sort.Direction.DESC, sorting));

        var tasks = _taskRepository.findByUserFiltered(userId, filtersDTO.content(), filtersDTO.priority(),
                filtersDTO.startDate(), filtersDTO.endDate(), filtersDTO.done(), pagination).orElseThrow();

        return TaskResponseDTO.fromList(tasks);
    }

    @Override
    public TaskResponseDTO getTaskById(UUID taskId) {
        var task = _taskRepository.findById(taskId).orElseThrow();
        return TaskResponseDTO.from(task);
    }

    @Override
    public TaskResponseDTO updateTask(UUID taskId, UpdateTaskRequestDTO updateTaskRequestDTO) {
        var task = _taskRepository.findById(taskId).orElseThrow();

        task.setTitle(updateTaskRequestDTO.title());
        task.setDescription(updateTaskRequestDTO.description());
        task.setDueDate(updateTaskRequestDTO.dueDate());
        task.setPriority(updateTaskRequestDTO.priority());
        task.setDone(updateTaskRequestDTO.done());
        task.setDeleted(updateTaskRequestDTO.deleted());

        _taskRepository.save(task);

        return TaskResponseDTO.from(task);
    }

    @Override
    public boolean deleteTaskById(UUID taskId) {
        _taskRepository.deleteById(taskId);
        return true;
    }


}

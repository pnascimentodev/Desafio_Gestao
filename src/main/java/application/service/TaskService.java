package application.service;

import domain.entity.Project;
import domain.entity.Task;
import domain.enums.Priority;
import domain.enums.Status;
import domain.repository.ProjectRepository;
import domain.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import presentation.dto.TaskCreateDTO;
import presentation.dto.TaskResponseDTO;
import presentation.dto.TaskUpdateStatusDTO;
import application.mapper.TaskMapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponseDTO createTask(@Valid TaskCreateDTO taskCreateDTO) {
        Project project = validateAndGetProject(taskCreateDTO.projectId());
        Task task = taskMapper.toEntity(taskCreateDTO, project);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(savedTask);
    }

    public Optional<TaskResponseDTO> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toResponseDTO);
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    public TaskResponseDTO updateTaskStatus(Long id, @Valid TaskUpdateStatusDTO updateDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));

        task.setStatus(updateDTO.status());
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(updatedTask);
    }

    public List<TaskResponseDTO> findByFilters(Status status, Priority priority, Long projectId) {
        // Validate project exists if projectId is provided
        if (projectId != null) {
            validateAndGetProject(projectId);
        }

        return taskRepository.findByFilters(status, priority, projectId)
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    private Project validateAndGetProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + projectId + " not found"));
    }
}

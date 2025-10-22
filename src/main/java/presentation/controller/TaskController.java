package presentation.controller;

import application.mapper.TaskMapper;
import application.service.ProjectService;
import application.service.TaskService;
import domain.entity.Project;
import domain.entity.Task;
import domain.enums.Priority;
import domain.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentation.dto.TaskCreateDTO;
import presentation.dto.TaskResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management operations")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, ProjectService projectService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a new task with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Project project = projectService.findById(taskCreateDTO.projectId());
        if (project == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Task task = taskMapper.toEntity(taskCreateDTO, project);
        Task savedTask = taskService.save(task);
        TaskResponseDTO responseDTO = taskMapper.toResponseDTO(savedTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping
    @Operation(summary = "Update an existing task", description = "Updates an existing task with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<TaskResponseDTO> updateTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Project project = projectService.findById(taskCreateDTO.projectId());
        if (project == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Task task = taskMapper.toEntity(taskCreateDTO, project);
        Task updatedTask = taskService.save(task);
        TaskResponseDTO responseDTO = taskMapper.toResponseDTO(updatedTask);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping
    @Operation(summary = "Delete a task", description = "Deletes a task by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteTask(@RequestParam Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @Operation(summary = "Get tasks", description = "Get tasks with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<Iterable<TaskResponseDTO>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long projectId) {

        List<Task> tasks = taskService.findByFilters(status, priority, projectId);
        List<TaskResponseDTO> responseDTOs = tasks.stream()
                .map(taskMapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }
}

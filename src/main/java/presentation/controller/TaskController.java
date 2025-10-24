package presentation.controller;

import application.service.TaskService;
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
import presentation.dto.TaskUpdateStatusDTO;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Task management operations")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a new task with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        TaskResponseDTO responseDTO = taskService.createTask(taskCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update task status", description = "Updates only the status of an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateStatusDTO updateDTO) {
        TaskResponseDTO responseDTO = taskService.updateTaskStatus(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Deletes a task by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @Operation(summary = "Get tasks", description = "Get tasks with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<List<TaskResponseDTO>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long projectId) {

        List<TaskResponseDTO> responseDTOs = taskService.findByFilters(status, priority, projectId);
        return ResponseEntity.ok(responseDTOs);
    }
}

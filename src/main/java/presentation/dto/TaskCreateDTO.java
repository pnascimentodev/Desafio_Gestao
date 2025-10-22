package presentation.dto;
import domain.enums.Priority;
import domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
public record TaskCreateDTO(
    @NotBlank(message = "Task title is required")
    @Size(min = 5, max = 150, message = "Task title must be between 5 and 150 characters")
    String title,
    String description,
    @NotNull(message = "Status is required")
    Status status,
    @NotNull(message = "Priority is required")
    Priority priority,
    LocalDate dueDate,
    @NotNull(message = "Project ID is required")
    Long projectId
) {}

package presentation.dto;

import domain.enums.Priority;
import domain.enums.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDate dueDate,
    Long projectId,
    String projectName
) {}

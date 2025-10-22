package presentation.dto;

import domain.enums.Status;
import jakarta.validation.constraints.NotNull;

public record TaskUpdateStatusDTO(
    @NotNull(message = "Status is required")
    Status status
) {}


package presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ProjectCreateDTO(
    @NotBlank(message = "Project name must not be blank")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
    String name,

    String description,
    LocalDate startDate,
    LocalDate endDate
) {}

package presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO(
    String message,
    LocalDateTime timestamp,
    int status,
    String path,
    List<String> errors
) {
    public ErrorResponseDTO(String message, int status, String path) {
        this(message, LocalDateTime.now(), status, path, null);
    }

    public ErrorResponseDTO(String message, int status, String path, List<String> errors) {
        this(message, LocalDateTime.now(), status, path, errors);
    }
}

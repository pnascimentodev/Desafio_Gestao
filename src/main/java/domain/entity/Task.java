package domain.entity;

import domain.enums.Priority;
import domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task title must not be blank")
    @Size(min = 5, max = 150, message = "Task title must be between 5 and 150 characters")
    public String title;

    public String description;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Status id required")
    private Status status;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Priority is required")
    private Priority priority;

    public LocalDate dueDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "project_id", nullable = false)
    @NotBlank (message = "Project id required")
    private Project project;
}

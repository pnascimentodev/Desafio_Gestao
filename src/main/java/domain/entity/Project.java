package domain.entity;

import domain.enums.Priority;
import domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name must not be blank")
    @Size (min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
    public String name;

    public String description;

    public LocalDate startDate;

    public LocalDate endData;

}

package presentation.controller;

import application.mapper.ProjectMapper;
import application.service.ProjectService;
import domain.entity.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presentation.dto.ProjectCreateDTO;
import presentation.dto.ProjectResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "Project management operations")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new project", description = "Creates a new project with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Project created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        Project project = projectMapper.toEntity(projectCreateDTO);
        Project savedProject = projectService.save(project);
        ProjectResponseDTO responseDTO = projectMapper.toResponseDTO(savedProject);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Retrieves a project by its unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project found"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectResponseDTO> getProjectById(
            @Parameter(description = "Project ID", required = true) @PathVariable Long id) {
        Project project = projectService.findById(id);
        ProjectResponseDTO responseDTO = projectMapper.toResponseDTO(project);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieves a list of all projects")
    @ApiResponse(responseCode = "200", description = "List of projects retrieved successfully")
    public ResponseEntity<Iterable<ProjectResponseDTO>> getAllProjects() {
        Iterable<Project> projects = projectService.findAll();
        Iterable<ProjectResponseDTO> responseDTOs = ((java.util.Collection<Project>) projects)
                .stream()
                .map(projectMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }
}

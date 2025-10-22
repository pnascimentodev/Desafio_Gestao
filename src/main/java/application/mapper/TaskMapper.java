package application.mapper;

import domain.entity.Task;
import domain.entity.Project;
import presentation.dto.TaskCreateDTO;
import presentation.dto.TaskResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "priority", source = "dto.priority")
    @Mapping(target = "dueDate", source = "dto.dueDate")
    @Mapping(target = "project", source = "project")
    Task toEntity(TaskCreateDTO dto, Project project);

    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "projectName", source = "project.name")
    TaskResponseDTO toResponseDTO(Task task);
}


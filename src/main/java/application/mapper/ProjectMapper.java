package application.mapper;

import domain.entity.Project;
import presentation.dto.ProjectCreateDTO;
import presentation.dto.ProjectResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Project toEntity(ProjectCreateDTO dto);

    ProjectResponseDTO toResponseDTO(Project project);
}

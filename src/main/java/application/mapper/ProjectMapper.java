package application.mapper;

import domain.entity.Project;
import presentation.dto.ProjectCreateDTO;
import presentation.dto.ProjectResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endData", source = "endDate")
    Project toEntity(ProjectCreateDTO dto);

    @Mapping(target = "endDate", source = "endData")
    ProjectResponseDTO toResponseDTO(Project project);
}

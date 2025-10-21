package domain.repository;

import domain.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Project save(Project project);

    List<Project> findAll();

    Optional<Project>findById(Long id);
}

package domain.repository;

import domain.entity.Task;
import domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByStatus(Status status);

    Task findByPriority(Integer priority);

}

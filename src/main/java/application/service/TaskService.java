package application.service;

import domain.entity.Task;
import domain.enums.Status;
import domain.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(@Valid Task task){
        return taskRepository.save(task);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElse(null);
    }
    public Iterable<Task> findAll(){
        return taskRepository.findAll();
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    public Task update(@Valid Task task){
        return taskRepository.save(task);
    }

   public Task findByStatus (Status status){
        return taskRepository.findByStatus(status);

   }

    public Task findByPriority(Integer priority){
        return taskRepository.findByPriority(priority);
    }
}

package application.service;

import domain.entity.Task;
import domain.enums.Priority;
import domain.enums.Status;
import domain.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    public Task update(@Valid Task task){
        return taskRepository.save(task);
    }

    public List<Task> findByStatus(Status status){
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByPriority(Priority priority){
        return taskRepository.findByPriority(priority);
    }

    public List<Task> findByFilters(Status status, Priority priority, Long projectId){
        return taskRepository.findByFilters(status, priority, projectId);
    }
}

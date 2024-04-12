package br.com.myprojects.gestao_de_tarefas.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myprojects.gestao_de_tarefas.exceptions.customExceptions.NotFoundException;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskEntity createTask(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<TaskEntity> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public TaskEntity getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found."));
    }

    public void deleteTaskById(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task not found.");
        }
        taskRepository.deleteById(id);
    }
}

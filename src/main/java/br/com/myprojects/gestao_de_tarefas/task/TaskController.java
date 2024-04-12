package br.com.myprojects.gestao_de_tarefas.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myprojects.gestao_de_tarefas.exceptions.customExceptions.NotFoundException;
import br.com.myprojects.gestao_de_tarefas.exceptions.dtos.ErrorMessageDTO;
import br.com.myprojects.gestao_de_tarefas.task.dtos.TaskCreateDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskCreateDTO.getTitle());
        taskEntity.setStatus(taskCreateDTO.getStatus());
        taskEntity.setDescription(taskCreateDTO.getDescription());
        TaskEntity createdTask = taskService.createTask(taskEntity);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false) String status) {
        if (status != null) {
            List<TaskEntity> tasks = taskService.getTasksByStatus(status);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            List<TaskEntity> tasks = taskService.getAllTasks();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable UUID taskId) {
        try {
            TaskEntity task = taskService.getTaskById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NotFoundException e) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Task not found.");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable UUID taskId) {
        try {
            taskService.deleteTaskById(taskId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO("Task not found.");
            return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
        }
    }
}

package br.com.myprojects.gestao_de_tarefas.task;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String status;
    private String description;
}


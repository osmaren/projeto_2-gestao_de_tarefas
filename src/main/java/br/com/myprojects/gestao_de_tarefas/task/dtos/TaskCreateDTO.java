package br.com.myprojects.gestao_de_tarefas.task.dtos;

import lombok.Data;

@Data
public class TaskCreateDTO {
    private String title;
    private String status;
    private String description;
}
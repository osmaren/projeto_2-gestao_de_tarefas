package br.com.myprojects.gestao_de_tarefas.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String error;
}

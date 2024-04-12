package br.com.myprojects.gestao_de_tarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}

package br.com.myprojects.gestao_de_tarefas.exceptions.customExceptions;

import br.com.myprojects.gestao_de_tarefas.exceptions.ApiException;

// import exceptions.ApiException;

public class NotFoundException extends ApiException {
    public NotFoundException() {
        super("Not found.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}

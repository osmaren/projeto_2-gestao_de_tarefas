package br.com.myprojects.gestao_de_tarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.myprojects.gestao_de_tarefas.exceptions.customExceptions.NotFoundException;
import br.com.myprojects.gestao_de_tarefas.exceptions.dtos.ErrorMessageDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleNotFoundException(NotFoundException ex) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(ex.getMessage());
        return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
    }
}

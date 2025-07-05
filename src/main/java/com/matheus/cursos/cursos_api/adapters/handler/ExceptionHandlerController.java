package com.matheus.cursos.cursos_api.adapters.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();
        String msg = e.getMostSpecificCause().getMessage();

        if (msg.contains("from String")) {
            String field = msg.substring(msg.indexOf("`") + 1, msg.indexOf("`", msg.indexOf("`") + 1));
            String value = msg.substring(msg.indexOf("from String \"") + 13, msg.indexOf("\"", msg.indexOf("from String \"") + 13));
            dto.add(new ErrorMessageDTO("Valor inválido '" + value + "' para o campo " + field + ". Veja valores aceitos na documentação.", field));
        } else if (msg.contains("Unexpected character")) {
            dto.add(new ErrorMessageDTO("JSON mal formatado, verifique o Schema enviado", null));
        } else if (msg.contains("Cannot deserialize value of type")) {
            dto.add(new ErrorMessageDTO("Tipo de dado inválido ou campo obrigatório ausente. Verifique os campos enviados.", null));
        } else {
            dto.add(new ErrorMessageDTO("Erro ao ler o corpo da requisição: " + msg, null));
        }

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
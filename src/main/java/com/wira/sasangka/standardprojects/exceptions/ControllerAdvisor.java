package com.wira.sasangka.standardprojects.exceptions;

import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseStatusExceptionHandler {
    @ExceptionHandler({AppRuntimeException.class, ResourceNotFoundException.class, DataIntegrityViolationException.class,
            InternalServerException.class, NumberFormatException.class})
    public ResponseEntity<Object> illegalActionDataHandler(RuntimeException exception) {
        BaseResponse<Void> response = BaseResponse.<Void>builder()
                .responseCode(HttpStatus.CONFLICT.value())
                .responseMessage(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getFieldErrors()
                .stream().findFirst()
                .ifPresent(fieldError -> {
                    String defaultMessage = fieldError.getDefaultMessage();
                    String field = fieldError.getField();
                    message.append(StringUtils.capitalize(field))
                            .append(" ")
                            .append(defaultMessage);
                });

        BaseResponse<Void> response = BaseResponse.<Void>builder()
                .responseCode(HttpStatus.CONFLICT.value())
                .responseMessage(message.toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}

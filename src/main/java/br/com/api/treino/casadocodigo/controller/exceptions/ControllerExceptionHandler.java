package br.com.api.treino.casadocodigo.controller.exceptions;

import br.com.api.treino.casadocodigo.controller.model.ErrorResponse;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> dataIntegrityViolation(DuplicateKeyException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HashMap<String, String> errorMessage = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errorMessage.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(errorMessage.toString())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(NotFoundGatewayException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundGatewayException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}
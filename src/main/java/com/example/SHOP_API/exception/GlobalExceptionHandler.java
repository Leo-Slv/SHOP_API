package com.example.SHOP_API.exception;

import com.example.SHOP_API.controller.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erro quando usuário não existe
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(
            UserNotFoundException ex, WebRequest request) {
        ApiResponse<Void> response = ApiResponse.error(ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Erro de validação nos DTOs (usando @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        // Captura todos os erros de validação
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Captura erros de validação global (não associados a um campo específico)
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );

        ApiResponse<Map<String, String>> response = ApiResponse.error(
                "Validation failed for one or more fields",
                request.getDescription(false)
        );
        response.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Erro de validação direta na entidade (usando @NotNull, etc.)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage()
                ));

        ApiResponse<Map<String, String>> response = ApiResponse.error(
                "Constraint validation failed",
                request.getDescription(false)
        );
        response.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Erro de integridade do banco (unique constraint, foreign key, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex, WebRequest request) {

        String message = "Data integrity violation";

        // Personaliza mensagem baseada no tipo de erro
        if (ex.getMessage() != null) {
            if (ex.getMessage().contains("unique constraint") || ex.getMessage().contains("Duplicate entry")) {
                if (ex.getMessage().toLowerCase().contains("email")) {
                    message = "Email already exists";
                } else if (ex.getMessage().toLowerCase().contains("cpf")) {
                    message = "CPF already exists";
                } else {
                    message = "This record already exists";
                }
            } else if (ex.getMessage().contains("foreign key constraint")) {
                message = "Cannot delete record: it is referenced by other records";
            } else if (ex.getMessage().contains("not-null")) {
                message = "Required field cannot be null";
            }
        }

        ApiResponse<Void> response = ApiResponse.error(message, request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Erro de argumento ilegal (UUID inválido, etc.)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {

        String message = "Invalid input";
        if (ex.getMessage() != null && ex.getMessage().contains("Invalid UUID")) {
            message = "Invalid ID format";
        }

        ApiResponse<Void> response = ApiResponse.error(message, request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Erro genérico para capturar outros problemas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex, WebRequest request) {

        // Log do erro para debugar (em produção, use um logger real)
        System.err.println("Unexpected error: " + ex.getMessage());
        ex.printStackTrace();

        ApiResponse<Void> response = ApiResponse.error(
                "An unexpected error occurred",
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
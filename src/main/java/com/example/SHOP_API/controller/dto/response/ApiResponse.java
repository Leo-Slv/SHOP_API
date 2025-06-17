package com.example.SHOP_API.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;
    private Instant timestamp;
    private String path;

    // Construtores privados
    private ApiResponse(boolean success, String message, T data, String error, String path) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
        this.timestamp = Instant.now();
        this.path = path;
    }

    // Métodos estáticos para criar respostas de sucesso
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Operation completed successfully", data, null, null);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, null, null);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(true, message, null, null, null);
    }

    // Métodos estáticos para criar respostas de erro
    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(false, null, null, error, null);
    }

    public static <T> ApiResponse<T> error(String error, String path) {
        return new ApiResponse<>(false, null, null, error, path);
    }

    // Getters e Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}

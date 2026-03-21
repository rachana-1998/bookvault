package com.bookvault.bookvault.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String error;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> failure(String error) {
        return new ApiResponse<>(null, error, LocalDateTime.now());
    }
}

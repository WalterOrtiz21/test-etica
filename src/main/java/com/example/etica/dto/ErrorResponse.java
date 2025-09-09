package com.example.etica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de error estándar del API")
public class ErrorResponse {

    @Schema(description = "Tipo o categoría del error",
            example = "Empleado no encontrado")
    private String error;

    @Schema(description = "Mensaje detallado del error",
            example = "No existe un empleado con ID: 999")
    private String message;

    @Schema(description = "Fecha y hora cuando ocurrió el error",
            example = "2024-01-15T10:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Código de estado HTTP",
            example = "404")
    private int status;

    public ErrorResponse(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}

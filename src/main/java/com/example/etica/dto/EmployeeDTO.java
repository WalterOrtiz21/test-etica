package com.example.etica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "EmployeeDTO", description = "DTO para la transferencia de datos de Empleado")
public class EmployeeDTO {

    @Schema(description = "Identificador único del empleado",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre completo del empleado",
            example = "Juan Arrua",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 100)
    private String nombre;

    @NotBlank(message = "El puesto es obligatorio")
    @Schema(description = "Cargo o posición laboral del empleado",
            example = "Desarrollador",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 50)
    private String puesto;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor a 0")
    @Schema(description = "Salario mensual del empleado en la moneda local",
            example = "75000",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal salario;
}

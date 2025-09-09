package com.example.etica.controller;

import com.example.etica.dto.EmployeeDTO;
import com.example.etica.dto.ErrorResponse;
import com.example.etica.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Empleados", description = "API para la gestión de empleados")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Obtener todos los empleados",
               description = "Retorna la lista completa de empleados registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = EmployeeDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Obtener empleado por ID",
               description = "Busca y retorna un empleado específico utilizando su ID único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = EmployeeDTO.class),
                                      examples = @ExampleObject(value = """
                                          {
                                            "id": 1,
                                            "nombre": "Juan Arrua",
                                            "puesto": "Desarrollador",
                                            "salario": 75000
                                          }
                                          """))),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = ErrorResponse.class),
                                      examples = @ExampleObject(value = """
                                          {
                                            "error": "Empleado no encontrado",
                                            "message": "No existe un empleado con ID: 999",
                                            "timestamp": "2024-01-15T10:30:00",
                                            "status": 404
                                          }
                                          """)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(
            @Parameter(description = "ID único del empleado", example = "1", required = true)
            @PathVariable Long id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Empleado no encontrado",
                    "No existe un empleado con ID: " + id,
                    HttpStatus.NOT_FOUND.value()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @Operation(summary = "Buscar empleados por puesto",
               description = "Busca empleados que ocupen un puesto específico en la empresa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = EmployeeDTO.class),
                                      examples = @ExampleObject(value = """
                                          [
                                            {
                                              "id": 1,
                                              "nombre": "Juan Arrua",
                                              "puesto": "Desarrollador",
                                              "salario": 75000
                                            }
                                          ]
                                          """)))
    })
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeesByPuesto(
            @Parameter(description = "Nombre del puesto a buscar", example = "Desarrollador", required = true)
            @RequestParam String puesto) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByPuesto(puesto);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Crear nuevo empleado",
               description = "Crea un nuevo empleado en el sistema con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente",
                     content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = EmployeeDTO.class),
                                      examples = @ExampleObject(value = """
                                          {
                                            "id": 4,
                                            "nombre": "Ana García",
                                            "puesto": "Diseñadora",
                                            "salario": 60000
                                          }
                                          """))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                     content = @Content(mediaType = "application/json",
                                      examples = @ExampleObject(value = """
                                          {
                                            "error": "Errores de validación",
                                            "message": "Los datos proporcionados no son válidos",
                                            "fieldErrors": {
                                              "nombre": "El nombre es obligatorio",
                                              "salario": "El salario debe ser mayor a 0"
                                            }
                                          }
                                          """)))
    })
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(
            @Parameter(description = "Información del empleado a crear", required = true)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                content = @Content(examples = @ExampleObject(value = """
                    {
                      "nombre": "Ana García",
                      "puesto": "Diseñadora",
                      "salario": 60000
                    }
                    """)))
            @Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }
}

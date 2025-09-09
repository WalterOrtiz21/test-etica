package com.example.etica.service;

import com.example.etica.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(Long id);
    List<EmployeeDTO> getEmployeesByPuesto(String puesto);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}

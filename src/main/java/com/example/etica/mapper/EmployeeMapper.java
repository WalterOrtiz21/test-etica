package com.example.etica.mapper;

import com.example.etica.dto.EmployeeDTO;
import com.example.etica.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getNombre(),
                employee.getPuesto(),
                employee.getSalario()
        );
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setNombre(employeeDTO.getNombre());
        employee.setPuesto(employeeDTO.getPuesto());
        employee.setSalario(employeeDTO.getSalario());
        return employee;
    }
}

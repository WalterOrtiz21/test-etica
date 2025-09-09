package com.example.etica.service.impl;

import com.example.etica.dto.EmployeeDTO;
import com.example.etica.mapper.EmployeeMapper;
import com.example.etica.model.Employee;
import com.example.etica.repository.EmployeeRepository;
import com.example.etica.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByPuesto(String puesto) {
        return employeeRepository.findByPuesto(puesto)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employeeToSave = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employeeToSave);
        return employeeMapper.toDto(savedEmployee);
    }
}

package com.hasanalay.ems_backend.service.impl;

import com.hasanalay.ems_backend.dto.EmployeeDto;
import com.hasanalay.ems_backend.entity.Employee;
import com.hasanalay.ems_backend.exception.ResourceNotFoundException;
import com.hasanalay.ems_backend.mapper.EmployeeMapper;
import com.hasanalay.ems_backend.repository.IEmployeeRepository;
import com.hasanalay.ems_backend.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private IEmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee with the given id: "+ employeeId +" does not exists!"));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with the given id: "+ employeeId +" does not exists!"));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void removeEmployee(long employeeId) {

       Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                       () -> new ResourceNotFoundException("Employee with the given id: "+ employeeId +" does not exists!"));;
       employeeRepository.deleteById(employeeId);
    }
}

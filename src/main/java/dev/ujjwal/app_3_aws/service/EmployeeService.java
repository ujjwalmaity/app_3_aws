package dev.ujjwal.app_3_aws.service;

import dev.ujjwal.app_3_aws.dto.EmployeeDto;
import dev.ujjwal.app_3_aws.dto.EmployeeRegisterDto;
import dev.ujjwal.app_3_aws.entity.Employee;
import dev.ujjwal.app_3_aws.exception.ResourceNotFoundException;
import dev.ujjwal.app_3_aws.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    private final ResourceNotFoundException employeeNotFoundException = new ResourceNotFoundException("Employee not found");

    public EmployeeDto saveEmployee(EmployeeRegisterDto employeeRegisterDto) {
        Employee employee = modelMapper.map(employeeRegisterDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public EmployeeDto findEmployee(Long id) {
        Optional<Employee> opEmployee = employeeRepository.findById(id);
        if (opEmployee.isPresent()) {
            Employee employee = opEmployee.get();
            return modelMapper.map(employee, EmployeeDto.class);
        }
        log.warn(employeeNotFoundException.toString());
        throw employeeNotFoundException;
    }

    public List<EmployeeDto> findAllEmployee() {
        List<Employee> all = employeeRepository.findAll();
        return all.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getId() == null) {
            final ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("Invalid input");
            log.warn(resourceNotFoundException.toString());
            throw resourceNotFoundException;
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        findEmployee(employee.getId());
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    public void deleteEmployee(Long id) {
        findEmployee(id);
        employeeRepository.deleteById(id);
    }

}

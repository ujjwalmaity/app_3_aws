package dev.ujjwal.app_3_aws.controller;

import dev.ujjwal.app_3_aws.dto.EmployeeDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    private HttpServletRequest httpServletRequest;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findEmployee(@PathVariable Long id) {
        log.trace("{} {} {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), id);
        EmployeeDto employee = new EmployeeDto(1L, "Ujjwal", "Maity", "ujjwal@gmail.com", "123");
        log.trace(employee.toString());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}

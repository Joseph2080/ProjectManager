package org.chitsa.projectmanagement.controller;


import lombok.extern.log4j.Log4j;
import org.chitsa.projectmanagement.dto.EmployeeDto;
import org.chitsa.projectmanagement.exceptions.InvalidParameterException;
import org.chitsa.projectmanagement.model.Employee;
import org.chitsa.projectmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        log.info("Loading employees from database...");
        try {
            List<EmployeeDto> employees = employeeService.loadAll();
            if (employees.isEmpty()) {
                log.debug("No Employees we found in the database.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.debug("Employees were found successfully.");
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Error Found:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        log.info("Searching for employee id " + id + " from database....");
        try {
            EmployeeDto employeeDto = employeeService.loadById(id);
            if (employeeDto != null) {
                log.info("Employee with " + id + " has been found.");
                return new ResponseEntity<>(employeeDto, HttpStatus.OK);
            } else {
                log.info("No Employee with  " + id + " has been found in database.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        log.info("Saving employee in database...");
        try {
            employeeService.register(employeeDto);
            log.info("Client has been saved successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        log.info("Updating employee information...");
        try {
            log.info("Checking if employee  with  " + id + " exists...");
            employeeService.update(id, employeeDto);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidParameterException invalidParameterException) {
            log.error("Error Found: " + invalidParameterException.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Deleting Employee  from database...");
        try {
            log.info("Checking if Employee with  " + id + " exists...");
            employeeService.delete(id);
            log.info("Employee has been found and deleted successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidParameterException invalidParameterException) {
            log.error("Error Found: " + invalidParameterException.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
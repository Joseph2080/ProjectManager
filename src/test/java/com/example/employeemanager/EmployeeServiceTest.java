package com.example.employeemanager;

import org.chitsa.projectmanagement.dto.EmployeeDto;
import org.chitsa.projectmanagement.model.Employee;
import org.chitsa.projectmanagement.repo.EmployeeRepo;
import org.chitsa.projectmanagement.service.EmployeeService;
import org.chitsa.projectmanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepo);
    }

    @Test
    void testRegisterEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();

        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.register(employeeDto));
    }

    @Test
    void testUpdateEmployee() {
        Long employeeId = 1L;
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

        assertDoesNotThrow(() -> employeeService.update(employeeId, employeeDto));
    }

}

package org.chitsa.projectmanagement.service.impl;


import org.chitsa.projectmanagement.dto.EmployeeDto;
import org.chitsa.projectmanagement.model.Employee;
import org.chitsa.projectmanagement.repo.EmployeeRepo;
import org.chitsa.projectmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractServiceImpl<EmployeeRepo, Employee, EmployeeDto> implements EmployeeService {
    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        super(employeeRepo);
    }

    @Override
    protected void setId(Long id, Employee employee) {
        employee.setUserId(id);
    }

    @Override
    protected EmployeeDto convertModelToDto(Employee model) {
        return EmployeeDto.convertEmployeeToDto(model);
    }

    @Override
    protected Employee convertDtoToModel(EmployeeDto employeeDto) {
        return EmployeeDto.convertToEmployee(employeeDto);
    }
}

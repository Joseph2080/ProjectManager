package org.chitsa.projectmanagement.dto;

import lombok.Data;
import org.chitsa.projectmanagement.model.Employee;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDto extends UserDto {
    @NotEmpty(message = "Qualifications parameter provided is not valid")
    String qualifications;

    public EmployeeDto(@NotEmpty @NotEmpty(message = "Firstname name parameter can not be empty") String firstName
            , @NotEmpty(message = "Last name parameter can not be empty") String lastName
            , @NotEmpty(message = "Login parameter can not be empty") String login
            , @Email(message = "Email parameter provided is not valid") String email
            , @NotEmpty String dateRegistered, @NotEmpty(message = "role parameter can not be empty") String role
            , @NotEmpty(message = "Password parameter can not be empty") String password
            , @NotEmpty(message = "Verify password parameter can not be empty") String verifyPassword
            , boolean editable, String qualifications) {
        super(firstName, lastName, login, email, dateRegistered, role, password, verifyPassword, editable);
        this.qualifications = qualifications;
    }

    public EmployeeDto(){

    }
    public static Employee convertToEmployee(EmployeeDto employeeDto){
        Employee employee  = (Employee) convertToUser(employeeDto);
        employee.setQualifications(employeeDto.getQualifications());
        return employee;
    }

    public static EmployeeDto convertEmployeeToDto(Employee employee){
        EmployeeDto employeeDto = (EmployeeDto) convertUserToDto(employee);
        employeeDto.setQualifications(employee.getQualifications());
        return employeeDto;
    }
}

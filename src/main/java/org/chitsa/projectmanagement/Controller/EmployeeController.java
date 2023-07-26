package org.chitsa.projectmanagement.Controller;


import org.chitsa.projectmanagement.Model.Employee;
import org.chitsa.projectmanagement.Service.EmployeeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Log4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        log.info("Loading employees from database...");
        try {
            List<Employee> employees =  employeeService.getAll();
            if (employees.isEmpty()) {
                log.debug("No Employees we found in the database.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.debug("Employees were found successfully.");
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Error Found:" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        log.info("Searching for employee id " + id + " from database....");
        try {
            Employee employee = employeeService.findById(id);
            if(employee != null){
                log.info("Employee with " + id + " has been found.");
                 return new ResponseEntity<>(employee, HttpStatus.OK);
            }else{
                log.info("No Employee with  " + id + " has been found in database.");
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        log.info("Saving employee in database...");
        try {
            employeeService.save(employee);
            log.info("Client has been saved successfully.");
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }catch (Exception e){
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        log.info("Updating employee information...");
        try {
            log.info("Checking if employee  with  " + id + " exists...");
            Employee temp = employeeService.update(id,employee);
            if(temp!=null) {
                log.info("Employee has been found and information has been updated successfully.");
                return new ResponseEntity<>(temp,HttpStatus.OK);
            } else {
                log.info("Client with " + id + " does not exist");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        log.info("Deleting Employee  from database...");
        try {
            log.info("Checking if Employee with  " + id + " exists...");
            Employee temp = employeeService.findById(id);
            if(temp!=null){
                employeeService.deleteById(id);
                log.info("Employee has been found and deleted successfully.");
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                log.info("Employee with " + id + " does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
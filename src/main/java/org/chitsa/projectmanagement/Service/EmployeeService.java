package org.chitsa.projectmanagement.Service;

import org.chitsa.projectmanagement.Model.Employee;
import org.chitsa.projectmanagement.Repo.IEmployeeRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeService extends AbstractService<IEmployeeRepo,Employee> {

    public EmployeeService(IEmployeeRepo iEmployeeRepo){
        super(iEmployeeRepo);
    }

    public Employee update(Long id, Employee employee) {

        Optional<Employee> optionalEmployee = repo.findById(id);
        if(optionalEmployee.isPresent()) {
            Employee temp = repo.findById(id).get();
            temp.setUid(employee.getUid());
            temp.setFirstanme(employee.getFirstanme());
            temp.setLastname(employee.getLastname());
            temp.setRole(employee.getRole());
            repo.save(temp);
            return temp;
        }
        return null;
    }
}
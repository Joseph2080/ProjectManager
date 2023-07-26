package org.chitsa.projectmanagement.Repo;

import org.chitsa.projectmanagement.Model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepo extends CrudRepository<Employee, Long> {


}

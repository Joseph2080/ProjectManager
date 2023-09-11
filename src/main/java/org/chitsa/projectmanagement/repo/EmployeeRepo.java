package org.chitsa.projectmanagement.repo;

import org.chitsa.projectmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}

package org.chitsa.projectmanagement.repo;

import org.chitsa.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {

}

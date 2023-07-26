package org.chitsa.projectmanagement.Repo;

import org.chitsa.projectmanagement.Model.Project;
import org.springframework.data.repository.CrudRepository;

public interface IProjectsRepo extends CrudRepository<Project,Long> {

}

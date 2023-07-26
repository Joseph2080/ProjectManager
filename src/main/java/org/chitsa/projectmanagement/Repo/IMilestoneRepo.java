package org.chitsa.projectmanagement.Repo;

import org.chitsa.projectmanagement.Model.Milestone;
import org.springframework.data.repository.CrudRepository;

public interface IMilestoneRepo extends CrudRepository<Milestone,Long> {
}

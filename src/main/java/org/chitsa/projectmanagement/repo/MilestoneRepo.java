package org.chitsa.projectmanagement.repo;

import org.chitsa.projectmanagement.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepo extends JpaRepository<Milestone,Long> {
}

package org.chitsa.projectmanagement.service;


import org.chitsa.projectmanagement.dto.ProjectDto;
import org.chitsa.projectmanagement.model.Project;

import java.util.List;

public interface ProjectService extends Service<Project, ProjectDto>  {
    List<ProjectDto> loadAllProjectsByName(String name);
}
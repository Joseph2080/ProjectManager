package org.chitsa.projectmanagement.service.impl;

import org.chitsa.projectmanagement.dto.ProjectDto;
import org.chitsa.projectmanagement.model.Client;
import org.chitsa.projectmanagement.model.Project;
import org.chitsa.projectmanagement.repo.ClientRepo;
import org.chitsa.projectmanagement.repo.ProjectRepo;
import org.chitsa.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractServiceImpl<ProjectRepo, Project, ProjectDto> implements ProjectService {
    private ClientRepo clientRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo, ClientRepo clientRepo) {
        super(projectRepo);
        this.clientRepo = clientRepo;
    }

    @Override
    public List<ProjectDto> loadAllProjectsByName(String name) {
        return loadAll().stream().filter(project -> project.getProjectName().toLowerCase().
                contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    protected void setId(Long id, Project project) {
        project.setProjectId(id);
    }

    @Override
    protected ProjectDto convertModelToDto(Project model) {
        return ProjectDto.convertToProjectDto(model);
    }

    @Override
    public Project convertDtoToModel(ProjectDto projectDto) {
        Client client = clientRepo.findClientByLogin(projectDto.getClientLogin()).get();
        return ProjectDto.convertToProject(projectDto, client);
    }
}

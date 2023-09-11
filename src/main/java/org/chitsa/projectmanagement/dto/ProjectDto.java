package org.chitsa.projectmanagement.dto;


import lombok.Data;
import org.chitsa.projectmanagement.model.Client;
import org.chitsa.projectmanagement.model.Project;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProjectDto {
    @NotEmpty(message = "Project name parameter can not be empty")
    @Size(min = 5, max = 30)
    String projectName;
    @NotEmpty(message = "Client login parameter can not be empty")
    @Size(min = 5, max = 10)
    String clientLogin;
    @NotEmpty(message = "Project description parameter can not be empty")
    @Size(min = 5, max = 30)
    String description;

    public static Project convertToProject(ProjectDto projectDto, Client client) {
        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setClient(client);
        project.setProjectDescription(projectDto.getDescription());
        return project;
    }
    public static ProjectDto convertToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectName(project.getProjectName());
        projectDto.setClientLogin(project.getClient().getLogin());
        projectDto.setDescription(project.getProjectDescription());
        return projectDto;
    }

}

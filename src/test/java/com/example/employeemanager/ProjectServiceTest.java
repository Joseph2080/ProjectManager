package com.example.employeemanager;

import org.chitsa.projectmanagement.dto.ProjectDto;
import org.chitsa.projectmanagement.model.Project;
import org.chitsa.projectmanagement.repo.ClientRepo;
import org.chitsa.projectmanagement.repo.ProjectRepo;
import org.chitsa.projectmanagement.service.ProjectService;
import org.chitsa.projectmanagement.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private ClientRepo clientRepo;

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectServiceImpl(projectRepo, clientRepo);
    }

    @Test
    void testLoadAllProjectsByName() {
        String projectName = "Sample Project";
        Project project = new Project();

        when(projectRepo.findAll()).thenReturn(Collections.singletonList(project));

        List<ProjectDto> projects = projectService.loadAllProjectsByName(projectName);
        assertFalse(projects.isEmpty());
    }

}

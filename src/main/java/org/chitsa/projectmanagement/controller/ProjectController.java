package org.chitsa.projectmanagement.controller;

import lombok.extern.log4j.Log4j;
import org.chitsa.projectmanagement.dto.ProjectDto;
import org.chitsa.projectmanagement.exceptions.InvalidParameterException;
import org.chitsa.projectmanagement.model.Project;
import org.chitsa.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects() {
        log.info("Loading Projects from database...");
        try {
            List<ProjectDto> projects = projectService.loadAll();
            if (projects.isEmpty()) {
                log.info("No Projects were found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.info("Projects found and loaded successfully");
                return new ResponseEntity<>(projects, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findProjectById(@PathVariable Long id) {
        log.info("Finding project by  id...");
        try {
            ProjectDto projectDto = projectService.loadById(id);
            if (projectDto != null) {
                log.info("Project with " + id + "has been found");
                return new ResponseEntity<>(projectDto, HttpStatus.OK);
            } else {
                log.info("Project with " + id + " can not be found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error Found :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity saveProject(@RequestBody ProjectDto projectDto) {
        log.info("Saving Project to database...");
        try {
            projectService.register(projectDto);
            log.info("Project has been saved successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error Found :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        log.info("Updating Project information...");
        try {
            log.info("Checking if Project  with  " + id + " exists...");
            projectService.update(id, projectDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidParameterException invalidParameterException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Deleting Project from database...");
        projectService.delete(id);
        log.info("Project has been found and deleted successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

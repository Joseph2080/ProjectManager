package org.chitsa.projectmanagement.Controller;

import org.chitsa.projectmanagement.Model.Project;
import org.chitsa.projectmanagement.Service.ProjectService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Log4j
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(){
        log.info("Loading Projects from database...");
        try {
            List<Project> projects = projectService.getAll();
            if (projects.isEmpty()) {
                log.info("No Projects were found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.info("Projects found and loaded successfully");
                return new ResponseEntity<>(projects, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable Long id){
        log.info("Finding project by  id...");
        try {
            Project project = projectService.findById(id);
            if(projectService!=null){
                log.info("Project with " + id + "has been found");
                return new ResponseEntity<>(project, HttpStatus.OK);
            }else{
                log.info("Project with " + id + " can not be found!");
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error Found :" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Project> saveProject(@RequestBody Project project){
        log.info("Saving Project to database...");
        try {
            projectService.save(project);
            log.info("Project has been saved successfully." );
            return new ResponseEntity<>(project,HttpStatus.OK);
        }catch (Exception e){
            log.error("Error Found :" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{projectId}/client/{clientId}")
    public ResponseEntity<Project> setClientToProject(@PathVariable Long projectId, @PathVariable Long clientId){
        log.info("Adding client  "+ clientId + " to Project " + projectId + "...");
        try {
            Project project = projectService.setClientToProject(projectId,clientId);
            if(project!=null){
                log.info("Adding client "+ clientId + " to Project " + projectId + "...");
                return new ResponseEntity<>(project,HttpStatus.NOT_FOUND);
            }else{
                log.info("Either Client or Project id does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
        log.info("Updating Project information...");
        try {
            log.info("Checking if employee  with  " + id + " exists...");
            Project temp = projectService.updateProject(id,project);
            if(temp!=null) {
                log.info("Project has been found and information has been updated successfully.");
                return new ResponseEntity<>(temp,HttpStatus.OK);
            } else {
                log.info("Project  " + id + " does not exist");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            log.error("Error found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Deleting Project from database...");
        try {
            log.info("Checking if Project with  " + id + " exists...");
            Project temp = projectService.findById(id);
            if (temp != null) {
                projectService.deleteById(id);
                log.info("Project has been found and deleted successfully.");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("Project with " + id + " does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

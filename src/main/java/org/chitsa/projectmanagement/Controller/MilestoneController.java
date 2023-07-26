package org.chitsa.projectmanagement.Controller;


import org.chitsa.projectmanagement.Model.Milestone;
import org.chitsa.projectmanagement.Service.MilestoneService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;
    public MilestoneController(MilestoneService milestoneService){
        this.milestoneService = milestoneService;
    }

    @GetMapping
    public ResponseEntity<List<Milestone>> getMilestones(){
        log.info("Loading Milestones from database...");
        try {
            List<Milestone> milestones = milestoneService.getAll();
            if (milestones.isEmpty()) {
                log.info("No Milestones were found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.debug("Milestones found and loaded successfully");
                return new ResponseEntity<>(milestones, HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Error Found :" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable Long id){

        log.info("Searching for Milestone by id " + id + "...");
        try {
            Milestone milestone = milestoneService.findById(id);
            if(milestone!=null){
                log.info("Milestone with " + id + " can not be found!");
                return new ResponseEntity<>(milestone, HttpStatus.OK);
            }else{
                log.info("Milestone " + id + " has been found");
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error Found :" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Milestone> saveMilestone(@RequestBody Milestone milestone){

        log.info("Saving Milestone to database...");
        try {
            milestoneService.save(milestone);
            log.info("Milestone has been saved successfully." );
            return new ResponseEntity<>(milestone,HttpStatus.OK);
        }catch (Exception e){
            log.error("Error Found :" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable Long id,@RequestBody Milestone milestone){

        log.info("Updating Milestone " + id + "...");
        try {
            Milestone temp = milestoneService.updateMilestone(id,milestone);
            if(temp!=null){
                log.info("Milestone " + id + " has been updated successfully.");
                return new ResponseEntity<>(milestone,HttpStatus.OK);
            } else {
                log.info("No milestone with " + id + " has been found.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            log.error("Error Found:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{milestoneId}/client/{clientId}")
    public ResponseEntity<Milestone> addEmployeeToMilestone(@PathVariable Long milestoneId,@PathVariable Long employeeId){

        log.info("Adding employee "+ employeeId + " to Milestone " + milestoneId + "...");
        try {
            Milestone milestone = milestoneService.addEmployeeToMilestone(milestoneId,employeeId);
            if(milestone!=null){
                log.info("Employee " + employeeId + " has been added to milestone " + milestoneId + " successfully.");
                return new ResponseEntity<>(milestoneService.save(milestone),HttpStatus.OK);
            }else{
                log.info("Either milestone or employee id does not exist.");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error Found: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{milestoneId}/employees/{employeeId}")
    public ResponseEntity<Milestone> removeEmployeeFromMilestone(@PathVariable Long milestoneId,@PathVariable Long employeeId){
        log.info("Removing employee "+ employeeId + " from Milestone " + milestoneId + "...");
        try {
            Milestone milestone = milestoneService.removeEmployeeFromMilestone(milestoneId, employeeId);
            if (milestone != null) {
                log.info("Employee " + employeeId + " has been removed from Milestone " + milestoneId);
                return new ResponseEntity<>(milestone, HttpStatus.OK);
            } else {
                log.info("Either milestone or employee id does not exist.");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error Found:" + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){

        log.info("Deleting Milestone  from database...");
        try {
            log.info("Checking if Milestone with  " + id + " exists...");
            Milestone temp = milestoneService.findById(id);
            if(temp!=null){
                log.info("Milestone has been found and deleted successfully.");
                return new ResponseEntity<>(milestoneService.deleteById(id),HttpStatus.OK);
            }else{
                log.info("Milestone " + id + " does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error Occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package org.chitsa.projectmanagement.Service;

import org.chitsa.projectmanagement.Model.Employee;
import org.chitsa.projectmanagement.Model.Milestone;
import org.chitsa.projectmanagement.Repo.IEmployeeRepo;
import org.chitsa.projectmanagement.Repo.IMilestoneRepo;
import org.chitsa.projectmanagement.Repo.IProjectsRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class MilestoneService extends AbstractService<IMilestoneRepo, Milestone>{

    private IEmployeeRepo iEmployeeRepo;
    private IProjectsRepo iProjectsRepo;

    public MilestoneService(IMilestoneRepo iMilestoneRepo,IEmployeeRepo iEmployeeRepo,IProjectsRepo iProjectsRepo){
        super(iMilestoneRepo);
        this.iEmployeeRepo = iEmployeeRepo;
        this.iProjectsRepo = iProjectsRepo;
    }

    public Milestone  addEmployeeToMilestone(Long milestoneId,Long employeeId){
        Optional<Milestone> optionalMilestone = repo.findById(milestoneId);
        Optional<Employee> optionalEmployee = iEmployeeRepo.findById(employeeId);

        if(optionalMilestone.isPresent()&&optionalEmployee.isPresent()){
            Milestone milestone = optionalMilestone.get();
            Employee employee = optionalEmployee.get();
            Set<Employee> projectEmployees = milestone.getEmployees();
            projectEmployees.add(employee);
            milestone.setEmployees(projectEmployees);
            repo.save(milestone);
            return milestone;
        }
        return null;
    }

    public Milestone updateMilestone(Long id,Milestone milestone){
        Optional<Milestone> optionalMilestone = repo.findById(id);

        if(optionalMilestone.isPresent()){
            Milestone temp = optionalMilestone.get();
            temp.setMilestoneId(milestone.getMilestoneId());
            temp.setMilestoneTitle(milestone.getMilestoneTitle());
            temp.setDescription(milestone.getDescription());
            temp.setNotes(milestone.getNotes());
            temp.setDateCreated(milestone.getDateCreated());
            temp.setDueDate(new Date());
            temp.setFee(milestone.getFee());
            repo.save(temp);
            return temp;
            }
        return null;
    }

    public Milestone removeEmployeeFromMilestone(Long milestoneId,Long employeeId){
        Optional<Milestone> optionalMilestone = repo.findById(milestoneId);
        Optional<Employee> optionalEmployee = iEmployeeRepo.findById(employeeId);

        if(optionalMilestone.isPresent()&&optionalEmployee.isPresent()) {
            Milestone milestone = optionalMilestone.get();
            Set<Employee> projectEmployees = milestone.getEmployees();
            projectEmployees.removeIf((employee1 -> employee1.getUid().equals(employeeId)));
            milestone.setEmployees(projectEmployees);
            repo.save(milestone);
            return milestone;
        }
        return null;
    }

}

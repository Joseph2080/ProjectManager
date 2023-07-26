package org.chitsa.projectmanagement.Service;


import org.chitsa.projectmanagement.Model.Project;
import org.chitsa.projectmanagement.Model.Client;
import org.chitsa.projectmanagement.Repo.IClientRepo;
import org.chitsa.projectmanagement.Repo.IProjectsRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProjectService extends AbstractService<IProjectsRepo, Project> {

    IClientRepo iClientRepo;
    public ProjectService(IProjectsRepo iProjectsRepo,IClientRepo iClientRepo){
        super(iProjectsRepo);
        this.iClientRepo = iClientRepo;
    }

    public Project setClientToProject(Long projectId,Long clientId){


        Optional<Project> optionalProjects = repo.findById(projectId);
        Optional<Client> optionalClient = iClientRepo.findById(clientId);
        if (optionalProjects.isPresent()&&optionalClient.isPresent()) {
            Project project = optionalProjects.get();
            Client client = optionalClient.get();
            project.setClient(client);
            repo.save(project);
            return project;
        }
        return null;
    }
    public Project updateProject(Long id,Project project) {
        Optional<Project> optionalProjects = repo.findById(id);
        if (optionalProjects.isPresent()){
            Project temp = optionalProjects.get();
            temp.setProjectId(project.getProjectId());
            temp.setProjectName(project.getProjectName());
            temp.setProjectDescription(project.getProjectDescription());
            temp.setClient(project.getClient());
            repo.save(temp);
            return temp;
        }
        return null;
    }
}

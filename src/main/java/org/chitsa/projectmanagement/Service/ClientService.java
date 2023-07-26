package org.chitsa.projectmanagement.Service;

import org.chitsa.projectmanagement.Model.Client;
import org.chitsa.projectmanagement.Repo.IClientRepo;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<IClientRepo, Client>{
    public ClientService(IClientRepo iClientRepo){
        super(iClientRepo);
    }

   public Client update(Long id, Client object) {

        if(repo.findById(id).isPresent()) {
            Client temp = new Client();
            temp.setUid(object.getUid());
            temp.setEmail(object.getEmail());
            temp.setPhoneNumber(object.getPhoneNumber());
            temp.setFirstanme(object.getFirstanme());
            temp.setLastname(object.getLastname());
            temp.setDescription(object.getDescription());
            repo.save(temp);
            return temp;
        }
        return null;
    }


}

package org.chitsa.projectmanagement.Controller;

import org.chitsa.projectmanagement.Model.Client;
import org.chitsa.projectmanagement.Service.ClientService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {


    @Autowired
    private ClientService clientService;
   public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getClients(){

        log.info("Loading clients from database...");
        try {
            List<Client> clients = clientService.getAll();
            if (clients.isEmpty()) {
                log.info("No Clients were found in database :(");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.info("Clients found in the database :)");
                return new ResponseEntity<>(clients, HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        log.info("Loading client id from database....");
       try {
            Client client = clientService.findById(id);
            if(client != null){
                log.info("Client with " + id + " has been found.");
                return new ResponseEntity<>(client, HttpStatus.OK);
            }
            else{
                log.info("No Client with  " + id + " has been found in database. Client may have not been recorded or has been deleted from database.");
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
           log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Client> saveClient( @RequestBody Client client){
        log.info("Saving  client in database...");
        try {
            clientService.save(client);
            log.info("Client has been saved  successfully.");
            return new ResponseEntity<>(client,HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Client> updatedClient(@PathVariable Long id,@RequestBody Client client){
        log.info("Updating client information...");
        try {
            log.info("Checking if client with  " + id + " exists...");
            Client temp = clientService.update(id,client);
            if (temp!=null) {
                log.info("Client has been found and information has been updated successfully.");
                return new ResponseEntity<>(temp, HttpStatus.OK);
            } else {
                log.info("Client with " + id + " does not exist");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }  catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteById(@PathVariable Long id){
        log.info("Deleting client  from database...");
        try {
            log.info("Checking if client with  " + id + " exists...");
            Client temp = clientService.findById(id);
            if(temp!=null){
                clientService.deleteById(id);
                log.info("Client has been found and deleted successfully.");
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                log.info("Client with " + id + " does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

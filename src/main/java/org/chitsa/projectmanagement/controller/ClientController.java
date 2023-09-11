package org.chitsa.projectmanagement.controller;

import lombok.extern.log4j.Log4j;
import org.chitsa.projectmanagement.dto.ClientDto;
import org.chitsa.projectmanagement.service.ClientService;
import org.chitsa.projectmanagement.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDto>> getClients() {
        log.info("Loading clients from database...");
        List<ClientDto> clients = clientService.loadAll();
        if (clients == null) {
            log.info("No Clients were found in database :(");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Clients found in the database :)");
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        log.info("Loading client id from database....");
        try {
            ClientDto clientDto = clientService.loadById(id);
            if (clientDto != null) {
                log.info("Client with " + id + " has been found.");
                return new ResponseEntity<>(clientDto, HttpStatus.OK);
            } else {
                log.info("No Client with  " + id + " has been found in database. Client may have not been recorded or has been deleted from database.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity registerClient(@RequestBody ClientDto clientDto) {
        log.info("Saving  client in database...");
        clientService.register(clientDto);
        log.info("Client has been saved  successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatedClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        log.info("Updating client information...");
        try {
            log.info("Checking if client with  " + id + " exists...");
            clientService.update(id, clientDto);
        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> deleteById(@PathVariable Long id) {
        log.info("Deleting client  from database...");
        try {
            log.info("Checking if client with  " + id + " exists...");
            ClientDto tempClientDto = clientService.loadById(id);
            if (tempClientDto != null) {
                clientService.delete(id);
                log.info("Client has been found and deleted successfully.");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("Client with " + id + " does not exist.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

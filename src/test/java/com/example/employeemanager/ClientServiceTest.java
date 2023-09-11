package com.example.employeemanager;
import org.chitsa.projectmanagement.dto.ClientDto;
import org.chitsa.projectmanagement.model.Client;
import org.chitsa.projectmanagement.repo.ClientRepo;
import org.chitsa.projectmanagement.service.ClientService;
import org.chitsa.projectmanagement.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepo clientRepo;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(clientRepo);
    }

    @Test
    void testLoadAllClients() {
        Client client = new Client(); // Set up client

        when(clientRepo.findAll()).thenReturn(Collections.singletonList(client));

        List<ClientDto> clients = clientService.loadAll();
        assertFalse(clients.isEmpty());
    }

    // Add more test methods for other scenarios
}

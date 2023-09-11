package org.chitsa.projectmanagement.service.impl;

import org.chitsa.projectmanagement.dto.ClientDto;
import org.chitsa.projectmanagement.model.Client;
import org.chitsa.projectmanagement.repo.ClientRepo;
import org.chitsa.projectmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl<S> extends AbstractServiceImpl<ClientRepo, Client, ClientDto> implements ClientService {
    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo) {
        super(clientRepo);
    }

    @Override
    protected void setId(Long id, Client client) {
        client.setUserId(id);
    }

    @Override
    protected ClientDto convertModelToDto(Client model) {
        return ClientDto.convertClientToDto(model);
    }

    @Override
    protected Client convertDtoToModel(ClientDto clientDto) {
        return ClientDto.convertToClient(clientDto);
    }
}

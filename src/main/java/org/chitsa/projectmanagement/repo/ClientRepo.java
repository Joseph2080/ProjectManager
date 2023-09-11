package org.chitsa.projectmanagement.repo;

import org.chitsa.projectmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client,Long> {
    Optional<Client> findClientByLogin(String login);
}

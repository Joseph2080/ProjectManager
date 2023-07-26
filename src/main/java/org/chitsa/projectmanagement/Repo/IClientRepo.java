package org.chitsa.projectmanagement.Repo;

import org.chitsa.projectmanagement.Model.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepo extends CrudRepository<Client,Long> {
}

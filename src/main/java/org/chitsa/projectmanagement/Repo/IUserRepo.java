package org.chitsa.projectmanagement.Repo;

import org.chitsa.projectmanagement.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepo extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);
}

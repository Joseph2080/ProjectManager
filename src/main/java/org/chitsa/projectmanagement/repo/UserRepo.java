package org.chitsa.projectmanagement.repo;

import org.chitsa.projectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByLogin(String login);

}

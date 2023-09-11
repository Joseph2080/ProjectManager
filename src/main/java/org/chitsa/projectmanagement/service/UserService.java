package org.chitsa.projectmanagement.service;

import org.chitsa.projectmanagement.dto.ProjectDto;
import org.chitsa.projectmanagement.dto.UserDto;
import org.chitsa.projectmanagement.model.Project;
import org.chitsa.projectmanagement.model.User;

import java.util.List;

public interface UserService extends Service<User, UserDto>{
    List<User> loadUsersByLogin(String login);
    User findByLogin(String login);
}
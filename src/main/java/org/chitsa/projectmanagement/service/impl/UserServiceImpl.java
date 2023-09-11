package org.chitsa.projectmanagement.service.impl;

import org.chitsa.projectmanagement.dto.UserDto;
import org.chitsa.projectmanagement.exceptions.InvalidParameterException;
import org.chitsa.projectmanagement.exceptions.UserNotFoundException;
import org.chitsa.projectmanagement.model.User;
import org.chitsa.projectmanagement.repo.UserRepo;
import org.chitsa.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserRepo, User, UserDto> implements UserService {
    @Autowired
    public UserServiceImpl(UserRepo repo) {
        super(repo);
    }

    @Override
    protected void setId(Long id, User user) {
        user.setUserId(id);
    }

    @Override
    protected UserDto convertModelToDto(User model) {
        return UserDto.convertUserToDto(model);
    }

    @Override
    protected User convertDtoToModel(UserDto userDto) {
        if (!userDto.verifyPassword()) {
            throw new InvalidParameterException("Passwords do not match!");
        }
        return UserDto.convertToUser(userDto);
    }

    @Override
    public List<User> loadUsersByLogin(String login) {
        return loadAll().stream().filter(user -> user.getLogin().contains(login)).
                collect(Collectors.toList());
    }

    @Override
    public User findByLogin(String login){
        return (repo.findUserByLogin(login).orElseThrow(() -> new UserNotFoundException("Unable to find " + login)));
    }
}

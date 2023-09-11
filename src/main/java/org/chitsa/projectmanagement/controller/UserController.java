package org.chitsa.projectmanagement.controller;

import lombok.extern.log4j.Log4j;
import org.chitsa.projectmanagement.dto.UserDto;
import org.chitsa.projectmanagement.exceptions.InvalidParameterException;
import org.chitsa.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Loading Users from database...");
        try {
            List<UserDto> users = userService.loadAll();
            if (users.isEmpty()) {
                log.info("No Users were found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.info("Users found and loaded successfully");
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        log.info("Finding User by  id...");
        try {
            UserDto userDto = userService.loadById(id);
            if (userDto != null) {
                log.info("User with " + id + "has been found");
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                log.info("User with " + id + " can not be found!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.info("Error Found :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        log.info("Saving User to database...");
        try {
            userService.register(userDto);
            log.info("User has been saved successfully.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error Found :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("Updating User information...");
        try {
            log.info("Checking if user  with  " + id + " exists...");
            userService.update(id, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidParameterException invalidParameterException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        log.info("Deleting User from database...");
        userService.delete(id);
        log.info("Project has been deleted successfully.");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

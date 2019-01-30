package com.arun.controller;

import com.arun.dal.UserRepository;
import com.arun.exception.UserNotFoundException;
import com.arun.model.User;
import com.arun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        return userRepository.save(user);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else
            throw new UserNotFoundException("No user found with given id" + userId);
    }

    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {

        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get().getUserSettings();
        } else
            throw new UserNotFoundException("No user found with given id" + userId);
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        User user = userRepository.findById(userId).get();
        if (user != null) {
            user.getUserSettings().put(key, value);
            userRepository.save(user);
            return "Key added";
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/uploadUserDetails", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public User addUserFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.saveUserFromFile(file);
    }

}

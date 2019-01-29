package com.arun.service;

import com.arun.dal.UserRepository;
import com.arun.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    public User saveUserFromFile(MultipartFile file) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(file.getBytes(), User.class);
        return userRepository.save(user);

    }

}

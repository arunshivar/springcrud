package com.arun.controller;

import com.arun.model.Person;
import com.arun.model.User;
import com.arun.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;
    @RequestMapping(value = "/csv")
    public String savePersonsFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personService.processFileToSave(file);
    }

    @PostMapping("/save")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @GetMapping("")
    public List<Person> getAllPersons(){
        return personService.getAllUsers();
    }
}

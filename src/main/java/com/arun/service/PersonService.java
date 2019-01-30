package com.arun.service;

import com.arun.dal.PersonRepository;
import com.arun.exception.StorageException;
import com.arun.model.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public String processFileToSave(MultipartFile file) throws IOException {

        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<Person> personIter =
                mapper.reader(Person.class).with(bootstrapSchema).readValues(file.getBytes());
        // MappingIterator<Person> personIter = new CsvMapper().readerWithTypedSchemaFor(Person.class).readValues(file.getBytes());

        List<Person> personsList = null;
        try {
            personsList = personIter.readAll();
        } catch (IOException e) {
            throw new StorageException("CSV file cannot be uploaded");
        }

        personsList.forEach(person -> {
            personRepository.save(person);
        });

        return "CSV file data uploaded successfully";
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }
    public List<Person> getAllUsers(){
        return personRepository.findAll();
    }

}

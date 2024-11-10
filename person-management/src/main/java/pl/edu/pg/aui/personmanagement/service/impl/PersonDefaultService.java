package pl.edu.pg.aui.personmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.aui.personmanagement.model.Person;
import pl.edu.pg.aui.personmanagement.repository.PersonRepository;
import pl.edu.pg.aui.personmanagement.service.api.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonDefaultService implements PersonService {

    private final PersonRepository personRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public PersonDefaultService(PersonRepository personRepository, RestTemplate restTemplate) {
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Person> find(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> find(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void create(Person person) {
        personRepository.save(person);
    }

    @Override
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        String url = "${person.management.url}" + id + "/cars";
        restTemplate.delete(url);
        personRepository.delete(person);
    }

    @Override
    public void delete(Person person) {
        Person tmp = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        String url = "${person.management.url}" + person.getId() + "/cars";
        restTemplate.delete(url);
        personRepository.delete(person);
    }
}

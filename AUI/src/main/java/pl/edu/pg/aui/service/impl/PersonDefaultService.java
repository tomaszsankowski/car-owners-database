package pl.edu.pg.aui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.repository.PersonRepository;
import pl.edu.pg.aui.service.api.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonDefaultService implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDefaultService(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
        personRepository.findById(id).ifPresent(personRepository::delete);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }
}

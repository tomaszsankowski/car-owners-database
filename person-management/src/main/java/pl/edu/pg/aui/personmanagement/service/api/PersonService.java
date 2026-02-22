package pl.edu.pg.aui.personmanagement.service.api;

import pl.edu.pg.aui.personmanagement.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Optional<Person> find(UUID id);

    Optional<Person> find(String name, String surname);

    List<Person> findAll();

    void create(Person person);

    void update(Person person);

    void delete(UUID id);

    void delete(Person person);

}

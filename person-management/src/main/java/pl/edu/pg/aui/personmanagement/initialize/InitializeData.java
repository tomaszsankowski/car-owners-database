package pl.edu.pg.aui.personmanagement.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.personmanagement.model.Person;
import pl.edu.pg.aui.personmanagement.service.api.PersonService;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final PersonService personService;

    @Autowired
    public InitializeData(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (personService.find("Admin", "Admin").isEmpty()) {

            Person person1 = Person.builder()
                    .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .name("Admin")
                    .surname("Admin")
                    .age(99)
                    .build();
            Person person2 = Person.builder()
                    .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                    .name("Jan")
                    .surname("Kowalski")
                    .age(30)
                    .build();
            Person person3 = Person.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                    .name("Anna")
                    .surname("Nowak")
                    .age(25)
                    .build();

            personService.create(person1);
            personService.create(person2);
            personService.create(person3);

        }

    }
}

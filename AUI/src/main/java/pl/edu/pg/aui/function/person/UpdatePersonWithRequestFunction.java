package pl.edu.pg.aui.function.person;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.person.PatchPersonRequest;
import pl.edu.pg.aui.model.Person;

import java.util.function.BiFunction;

@Component
public class UpdatePersonWithRequestFunction implements BiFunction<Person, PatchPersonRequest, Person> {

    @Override
    public Person apply(Person person, PatchPersonRequest patchPersonRequest) {
        return Person.builder()
                .id(person.getId())
                .name(patchPersonRequest.getName())
                .surname(patchPersonRequest.getSurname())
                .age(patchPersonRequest.getAge())
                .cars(person.getCars())
                .build();
    }
}

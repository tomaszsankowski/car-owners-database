package pl.edu.pg.aui.function.person;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.person.GetPersonResponse;
import pl.edu.pg.aui.model.Person;

import java.util.function.Function;

@Component
public class PersonToResponseFunction implements Function<Person, GetPersonResponse> {

    @Override
    public GetPersonResponse apply(Person person) {
        return GetPersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .surname(person.getSurname())
                .age(person.getAge())
                .build();
    }
}

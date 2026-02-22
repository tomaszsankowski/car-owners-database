package pl.edu.pg.aui.personmanagement.function;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.personmanagement.dto.GetPersonResponse;
import pl.edu.pg.aui.personmanagement.model.Person;

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

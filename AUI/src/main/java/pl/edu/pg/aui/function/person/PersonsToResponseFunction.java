package pl.edu.pg.aui.function.person;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.person.GetPersonsResponse;
import pl.edu.pg.aui.model.Person;

import java.util.List;
import java.util.function.Function;

@Component
public class PersonsToResponseFunction implements Function<List<Person>, GetPersonsResponse> {

    @Override
    public GetPersonsResponse apply(List<Person> persons) {
        return GetPersonsResponse.builder()
                .persons(persons.stream()
                        .map(person -> GetPersonsResponse.Person.builder()
                                .id(person.getId())
                                .name(person.getName())
                                .surname(person.getSurname())
                                .build())
                        .toList())
                .build();
    }
}

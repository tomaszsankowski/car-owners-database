package pl.edu.pg.aui.function.person;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.person.PutPersonRequest;
import pl.edu.pg.aui.model.Person;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UpdatePersonWithRequestFunction implements BiFunction<UUID, PutPersonRequest, Person> {

    @Override
    public Person apply(UUID id, PutPersonRequest putPersonRequest) {
        return Person.builder()
                .id(id)
                .name(putPersonRequest.getName())
                .surname(putPersonRequest.getSurname())
                .age(putPersonRequest.getAge())
                .build();
    }
}

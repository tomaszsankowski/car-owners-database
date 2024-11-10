package pl.edu.pg.aui.personmanagement.controller.impl;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.aui.personmanagement.controller.api.PersonController;
import pl.edu.pg.aui.personmanagement.dto.GetPersonResponse;
import pl.edu.pg.aui.personmanagement.dto.GetPersonsResponse;
import pl.edu.pg.aui.personmanagement.dto.PatchPersonRequest;
import pl.edu.pg.aui.personmanagement.dto.PutPersonRequest;
import pl.edu.pg.aui.personmanagement.function.PersonToResponseFunction;
import pl.edu.pg.aui.personmanagement.function.PersonsToResponseFunction;
import pl.edu.pg.aui.personmanagement.function.RequestToPersonFunction;
import pl.edu.pg.aui.personmanagement.function.UpdatePersonWithRequestFunction;
import pl.edu.pg.aui.personmanagement.service.api.PersonService;

import java.util.UUID;

@RestController
@Log
@Validated
public class PersonDefaultController implements PersonController {

    private final PersonService service;

    private final PersonsToResponseFunction personsToResponseFunction;

    private final PersonToResponseFunction personToResponseFunction;

    private final RequestToPersonFunction requestToPersonFunction;

    private final UpdatePersonWithRequestFunction updatePersonWithRequestFunction;

    @Autowired
    public PersonDefaultController(PersonService service,
                                   PersonsToResponseFunction personsToResponseFunction,
                                   PersonToResponseFunction personToResponseFunction,
                                   RequestToPersonFunction requestToPersonFunction,
                                   UpdatePersonWithRequestFunction updatePersonWithRequestFunction)
    {
        this.service = service;
        this.personsToResponseFunction = personsToResponseFunction;
        this.personToResponseFunction = personToResponseFunction;
        this.requestToPersonFunction = requestToPersonFunction;
        this.updatePersonWithRequestFunction = updatePersonWithRequestFunction;
    }

    @Override
    public GetPersonsResponse getPersons() {
        return personsToResponseFunction.apply(service.findAll());
    }

    @Override
    public GetPersonResponse getPerson(UUID id) {
        return service.find(id)
                .map(personToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    @Override
    public void putPerson(UUID id, @Valid @RequestBody PutPersonRequest person) {
        service.create(requestToPersonFunction.apply(id, person));
    }

    @Override
    public void patchPerson(UUID id, @Valid @RequestBody PatchPersonRequest person) {
        service.update(updatePersonWithRequestFunction.apply(service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found")), person));
    }

    @Override
    public void deletePerson(UUID id) {
        try {
            service.delete(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
    }
}

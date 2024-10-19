package pl.edu.pg.aui.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.dto.person.GetPersonResponse;
import pl.edu.pg.aui.dto.person.GetPersonsResponse;
import pl.edu.pg.aui.dto.person.PatchPersonRequest;
import pl.edu.pg.aui.dto.person.PutPersonRequest;

import java.util.UUID;

public interface PersonController {

    @GetMapping("api/persons")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPersonsResponse getPersons();

    @GetMapping("api/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPersonResponse getPerson(@PathVariable("id") UUID id);

    @PutMapping("api/persons/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putPerson(@PathVariable("id") UUID id, @RequestBody PutPersonRequest person);

    @PatchMapping("api/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchPerson(@PathVariable("id") UUID id, @RequestBody PatchPersonRequest person);

    @DeleteMapping("api/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable("id") UUID id);

}

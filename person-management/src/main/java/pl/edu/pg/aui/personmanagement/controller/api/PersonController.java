package pl.edu.pg.aui.personmanagement.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.personmanagement.dto.GetPersonResponse;
import pl.edu.pg.aui.personmanagement.dto.GetPersonsResponse;
import pl.edu.pg.aui.personmanagement.dto.PatchPersonRequest;
import pl.edu.pg.aui.personmanagement.dto.PutPersonRequest;

import jakarta.validation.Valid;
import java.util.UUID;

@Validated
@RequestMapping("api/persons")
public interface PersonController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPersonsResponse getPersons();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPersonResponse getPerson(@PathVariable("id") UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putPerson(@PathVariable("id") UUID id, @Valid @RequestBody PutPersonRequest person);

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchPerson(@PathVariable("id") UUID id, @Valid @RequestBody PatchPersonRequest person);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable("id") UUID id);

}

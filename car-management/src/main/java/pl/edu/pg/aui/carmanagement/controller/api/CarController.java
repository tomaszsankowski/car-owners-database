package pl.edu.pg.aui.carmanagement.controller.api;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.carmanagement.dto.GetCarResponse;
import pl.edu.pg.aui.carmanagement.dto.GetCarsResponse;
import pl.edu.pg.aui.carmanagement.dto.PatchCarRequest;
import pl.edu.pg.aui.carmanagement.dto.PutCarRequest;

import java.util.UUID;

@RequestMapping("api")
@Validated
public interface CarController {

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarsResponse getCars();

    @GetMapping("/persons/{userId}/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarsResponse getUserCars(@PathVariable UUID userId);

    @GetMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarResponse getCar(@PathVariable("id") UUID id);

    @PutMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putCar(@PathVariable("id") UUID id, @Valid @RequestBody PutCarRequest car);

    @PatchMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchCar(@PathVariable("id") UUID id, @Valid @RequestBody PatchCarRequest car);

    @DeleteMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCar(@PathVariable("id") UUID id);

}

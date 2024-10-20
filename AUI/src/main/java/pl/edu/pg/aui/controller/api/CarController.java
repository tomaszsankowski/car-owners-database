package pl.edu.pg.aui.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pg.aui.dto.car.GetCarResponse;
import pl.edu.pg.aui.dto.car.GetCarsResponse;
import pl.edu.pg.aui.dto.car.PatchCarRequest;
import pl.edu.pg.aui.dto.car.PutCarRequest;

import java.util.UUID;

public interface CarController {

    @GetMapping("api/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarsResponse getCars();

    @GetMapping("api/persons/{userId}/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarsResponse getUserCars(@PathVariable UUID userId);

    @GetMapping("api/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetCarResponse getCar(@PathVariable("id") UUID id);

    @PutMapping("api/cars/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putCar(@PathVariable("id") UUID id, @RequestBody PutCarRequest car);

    @PatchMapping("api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchCar(@PathVariable("id") UUID id, @RequestBody PatchCarRequest car);

    @DeleteMapping("api/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCar(@PathVariable("id") UUID id);

}

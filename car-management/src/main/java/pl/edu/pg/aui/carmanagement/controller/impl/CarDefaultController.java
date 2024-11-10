package pl.edu.pg.aui.carmanagement.controller.impl;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.aui.carmanagement.controller.api.CarController;
import pl.edu.pg.aui.carmanagement.dto.GetCarResponse;
import pl.edu.pg.aui.carmanagement.dto.GetCarsResponse;
import pl.edu.pg.aui.carmanagement.dto.PatchCarRequest;
import pl.edu.pg.aui.carmanagement.dto.PutCarRequest;
import pl.edu.pg.aui.carmanagement.function.CarToResponseFunction;
import pl.edu.pg.aui.carmanagement.function.CarsToResponseFunction;
import pl.edu.pg.aui.carmanagement.function.RequestToCarFunction;
import pl.edu.pg.aui.carmanagement.function.UpdateCarWithRequestFunction;
import pl.edu.pg.aui.carmanagement.service.api.CarService;

import java.util.UUID;

@RestController
@Log
@Validated
public class CarDefaultController implements CarController {

    private final CarService service;

    private final CarsToResponseFunction carsToResponseFunction;

    private final CarToResponseFunction carToResponseFunction;

    private final RequestToCarFunction requestToCarFunction;

    private final UpdateCarWithRequestFunction updateCarWithRequestFunction;

    @Autowired
    public CarDefaultController(CarService service,
                                CarsToResponseFunction carsToResponseFunction,
                                CarToResponseFunction carToResponseFunction,
                                RequestToCarFunction requestToCarFunction,
                                UpdateCarWithRequestFunction updateCarWithRequestFunction) {
        this.service = service;
        this.carsToResponseFunction = carsToResponseFunction;
        this.carToResponseFunction = carToResponseFunction;
        this.requestToCarFunction = requestToCarFunction;
        this.updateCarWithRequestFunction = updateCarWithRequestFunction;
    }

    @Override
    public GetCarsResponse getCars() {
        return carsToResponseFunction.apply(service.findAll());
    }

    @Override
    public GetCarResponse getCar(UUID id) {
        return service.find(id)
                .map(carToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    }

    @Override
    public GetCarsResponse getUserCars(UUID userId) {
        return service.findAllByOwnerId(userId)
                .map(carsToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
    }

    @Override
    public void putCar(UUID id, @Valid @RequestBody PutCarRequest car) {
        try {
            service.create(requestToCarFunction.apply(id, car));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found", e);
        }
    }

    @Override
    public void patchCar(UUID id, @Valid @RequestBody PatchCarRequest car) {
        service.update(updateCarWithRequestFunction.apply(service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found")), car));
    }

    @Override
    public void deleteCar(UUID id) {
        try {
            service.delete(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found", e);
        }
    }

    @Override
    public void deleteAllUserCars(UUID userId) {
        try {
            service.deleteAllUserCars(userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found", e);
        }
    }

}
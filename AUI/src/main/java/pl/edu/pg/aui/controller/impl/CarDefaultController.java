package pl.edu.pg.aui.controller.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.aui.controller.api.CarController;
import pl.edu.pg.aui.dto.car.GetCarResponse;
import pl.edu.pg.aui.dto.car.GetCarsResponse;
import pl.edu.pg.aui.dto.car.PatchCarRequest;
import pl.edu.pg.aui.dto.car.PutCarRequest;
import pl.edu.pg.aui.function.car.CarsToResponseFunction;
import pl.edu.pg.aui.function.car.CarToResponseFunction;
import pl.edu.pg.aui.function.car.RequestToCarFunction;
import pl.edu.pg.aui.function.car.UpdateCarWithRequestFunction;
import pl.edu.pg.aui.service.api.CarService;

import java.util.UUID;

@RestController
@Log
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
    public GetCarsResponse getUserCars(UUID userId) {
        return service.findAllByOwner(userId)
                .map(carsToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public GetCarResponse getCar(UUID id) {
        return service.find(id)
                .map(carToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    }

    @Override
    public void putCar(UUID id, PutCarRequest car) {
        service.create(requestToCarFunction.apply(id, car));
    }

    @Override
    public void patchCar(UUID id, PatchCarRequest car) {
        service.update(updateCarWithRequestFunction.apply(service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found")), car));
    }

    @Override
    public void deleteCar(UUID id) {
        service.delete(id);
    }

}
package pl.edu.pg.aui.controller.api;

import pl.edu.pg.aui.dto.car.GetCarResponse;
import pl.edu.pg.aui.dto.car.GetCarsResponse;

import java.util.UUID;

public interface CarController {

    GetCarsResponse getCars();

    GetCarsResponse getUserCars(UUID userId);

    GetCarResponse getCar(UUID id);

    void putCar(UUID id, GetCarResponse car);

    void deleteCar(UUID id);
}

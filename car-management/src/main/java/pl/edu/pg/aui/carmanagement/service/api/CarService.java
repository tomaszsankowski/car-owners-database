package pl.edu.pg.aui.carmanagement.service.api;

import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {

    Optional<Car> find(UUID id);

    Optional<Car> find(String plate);

    Optional<Car> find(String brand, String model);

    List<Car> findAll();

    Optional<List<Car>> findAllByOwnerId(UUID owner);

    void create(Car car);

    void update(Car car);

    void delete(UUID id);

    void delete(String plate);

    void delete(String brand, String model);

    void delete(Car car);

    void deleteAllUserCars(UUID userId);

}

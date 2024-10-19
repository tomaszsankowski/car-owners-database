package pl.edu.pg.aui.service.api;

import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarService {

    Optional<Car> find(UUID id);

    Optional<Car> find(String plate);

    Optional<Car> find(String brand, String model);

    List<Car> findAll();

    List<Car> findAll(Person owner);

    List<Car> findAll(UUID ownerId);

    List<Car> findAll(String name, String surname);

    void create(Car car);

    void update(Car car);

    void delete(UUID id);

    void delete(String plate);

    void delete(String brand, String model);

    void delete(Car car);
}

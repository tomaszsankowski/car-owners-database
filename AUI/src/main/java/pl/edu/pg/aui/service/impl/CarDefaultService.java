package pl.edu.pg.aui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.repository.CarRepository;
import pl.edu.pg.aui.repository.PersonRepository;
import pl.edu.pg.aui.service.api.CarService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarDefaultService implements CarService {

    private final CarRepository carRepository;

    private final PersonRepository personRepository;

    @Autowired
    public CarDefaultService(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Car> find(UUID id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> find(String plate) {
        return carRepository.findByPlate(plate);
    }

    @Override
    public Optional<Car> find(String brand, String model) {
        return carRepository.findByBrandAndModel(brand, model);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAll(Person owner) {
        return carRepository.findAllByOwner(owner);
    }

    @Override
    public Optional<List<Car>> findAllByOwner(UUID ownerId) {
        return personRepository.findById(ownerId)
                .map(carRepository::findAllByOwner);
    }

    @Override
    public void create(Car car) {
        carRepository.save(car);
    }

    @Override
    public void update(Car car) {
        carRepository.save(car);
    }

    @Override
    public void delete(UUID id) {
        carRepository.findById(id).ifPresent(carRepository::delete);
    }

    @Override
    public void delete(String plate) {
        carRepository.findByPlate(plate).ifPresent(carRepository::delete);
    }

    @Override
    public void delete(String brand, String model) {
        carRepository.findByBrandAndModel(brand, model).ifPresent(carRepository::delete);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

}

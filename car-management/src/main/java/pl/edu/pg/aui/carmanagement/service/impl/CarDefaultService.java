package pl.edu.pg.aui.carmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.aui.carmanagement.dto.PutCarRequest;
import pl.edu.pg.aui.carmanagement.model.Car;
import pl.edu.pg.aui.carmanagement.repository.CarRepository;
import pl.edu.pg.aui.carmanagement.service.api.CarService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarDefaultService implements CarService {

    private final CarRepository carRepository;

    private final RestTemplate restTemplate;
    @Autowired
    public CarDefaultService(CarRepository carRepository, RestTemplate restTemplate) {
        this.carRepository = carRepository;
        this.restTemplate = restTemplate;
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
    public Optional<List<Car>> findAllByOwnerId(UUID ownerId) {
        try {
            String url = "${person.management.url}" + ownerId;
            restTemplate.getForObject(url, Void.class);
            List<Car> cars = carRepository.findAllByOwnerId(ownerId);
            return Optional.of(cars);
        } catch (HttpClientErrorException e) {
            return Optional.empty();
        }
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
        Car car = carRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        carRepository.delete(car);
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

    @Override
    public void deleteAllUserCars(UUID userId) {
        try {
            String url = "${person.management.url}/" + userId;
            restTemplate.getForObject(url, Void.class);
            List<Car> cars = carRepository.findAllByOwnerId(userId);
            carRepository.deleteAll(cars);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Owner not found");
        }
    }
}

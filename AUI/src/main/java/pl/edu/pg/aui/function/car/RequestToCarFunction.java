package pl.edu.pg.aui.function.car;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.car.PutCarRequest;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.repository.PersonRepository;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToCarFunction implements BiFunction<UUID, PutCarRequest, Car> {

    PersonRepository personRepository;

    public RequestToCarFunction(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Car apply(UUID id, PutCarRequest putCarRequest) {
        Person owner = personRepository.findById(putCarRequest.getOwner())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        return Car.builder()
                .id(id)
                .brand(putCarRequest.getBrand())
                .model(putCarRequest.getModel())
                .power(putCarRequest.getPower())
                .productionYear(putCarRequest.getProductionYear())
                .plate(putCarRequest.getPlate())
                .owner(owner)
                .build();
    }
}
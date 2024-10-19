package pl.edu.pg.aui.function.car;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.car.PutCarRequest;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class RequestToCarFunction implements BiFunction<UUID, PutCarRequest, Car> {

    @Override
    public Car apply(UUID id, PutCarRequest putCarRequest) {
        return Car.builder()
                .brand(putCarRequest.getBrand())
                .model(putCarRequest.getModel())
                .power(putCarRequest.getPower())
                .productionYear(putCarRequest.getProductionYear())
                .plate(putCarRequest.getPlate())
                .owner(Person.builder()
                        .id(putCarRequest.getOwner())
                        .build())
                .build();
    }
}

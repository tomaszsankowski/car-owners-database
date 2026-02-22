package pl.edu.pg.aui.carmanagement.function;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.carmanagement.dto.PatchCarRequest;
import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.function.BiFunction;

@Component
public class UpdateCarWithRequestFunction implements BiFunction<Car, PatchCarRequest, Car> {

    @Override
    public Car apply(Car car, PatchCarRequest patchCarRequest) {
        return Car.builder()
                .id(car.getId())
                .brand(patchCarRequest.getBrand())
                .model(patchCarRequest.getModel())
                .power(patchCarRequest.getPower())
                .productionYear(patchCarRequest.getProductionYear())
                .plate(car.getPlate())
                .ownerId(car.getOwnerId())
                .build();
    }
}

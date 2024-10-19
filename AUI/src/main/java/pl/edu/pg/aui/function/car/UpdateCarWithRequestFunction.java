package pl.edu.pg.aui.function.car;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.car.PatchCarRequest;
import pl.edu.pg.aui.model.Car;

import java.util.function.BiFunction;

@Component
public class UpdateCarWithRequestFunction implements BiFunction<Car, PatchCarRequest, Car> {

    @Override
    public Car apply(Car car, PatchCarRequest patchCarRequest) {
        return Car.builder()
                .brand(patchCarRequest.getBrand())
                .model(patchCarRequest.getModel())
                .power(patchCarRequest.getPower())
                .productionYear(patchCarRequest.getProductionYear())
                .plate(car.getPlate())
                .owner(car.getOwner())
                .build();
    }
}

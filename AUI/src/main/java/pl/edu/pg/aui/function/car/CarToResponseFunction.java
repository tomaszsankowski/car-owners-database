package pl.edu.pg.aui.function.car;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.dto.car.GetCarResponse;
import pl.edu.pg.aui.model.Car;

import java.util.function.Function;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    @Override
    public GetCarResponse apply(Car car) {
        return GetCarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .power(car.getPower())
                .productionYear(car.getProductionYear())
                .plate(car.getPlate())
                .owner(GetCarResponse.Person.builder()
                        .id(car.getOwner().getId())
                        .name(car.getOwner().getName())
                        .surname(car.getOwner().getSurname())
                        .build())
                .build();
    }
}

package pl.edu.pg.aui.carmanagement.function;

import org.springframework.stereotype.Component;
import pl.edu.pg.aui.carmanagement.dto.GetCarsResponse;
import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.List;
import java.util.function.Function;

@Component
public class CarsToResponseFunction implements Function<List<Car>, GetCarsResponse> {

    @Override
    public GetCarsResponse apply(List<Car> cars) {
        return GetCarsResponse.builder()
                .cars(cars.stream()
                        .map(car -> GetCarsResponse.Car.builder()
                                .id(car.getId())
                                .brand(car.getBrand())
                                .model(car.getModel())
                                .build())
                        .toList())
                .build();
    }
}

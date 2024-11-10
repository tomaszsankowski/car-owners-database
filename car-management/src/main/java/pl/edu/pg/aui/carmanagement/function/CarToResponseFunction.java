package pl.edu.pg.aui.carmanagement.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.aui.carmanagement.dto.GetCarResponse;
import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.function.Function;
import java.util.UUID;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    private final RestTemplate restTemplate;

    @Autowired
    public CarToResponseFunction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GetCarResponse apply(Car car) {
        try {
            String url = "${person.management.url}" + car.getOwnerId();
            GetCarResponse.Person owner = restTemplate.getForObject(url, GetCarResponse.Person.class);
            return GetCarResponse.builder()
                    .id(car.getId())
                    .brand(car.getBrand())
                    .model(car.getModel())
                    .power(car.getPower())
                    .productionYear(car.getProductionYear())
                    .plate(car.getPlate())
                    .owner(owner)
                    .build();
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Owner not found");
        }
    }

}

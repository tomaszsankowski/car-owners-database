package pl.edu.pg.aui.carmanagement.function;

import org.springframework.beans.factory.annotation.Autowired;
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
        GetCarResponse.Person owner = fetchOwnerData(car.getOwnerId());

        return GetCarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .power(car.getPower())
                .productionYear(car.getProductionYear())
                .plate(car.getPlate())
                .owner(owner)
                .build();
    }

    private GetCarResponse.Person fetchOwnerData(UUID ownerId) {
        try {
            String url = "http://localhost:8080/persons/" + ownerId;
            return restTemplate.getForObject(url, GetCarResponse.Person.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Owner not found");
        }
    }
}

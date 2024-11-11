package pl.edu.pg.aui.carmanagement.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.aui.carmanagement.dto.GetCarResponse;
import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.function.Function;

@Component
public class CarToResponseFunction implements Function<Car, GetCarResponse> {

    private final RestTemplate restTemplate;

    private final String personManagementUrl;

    @Autowired
    public CarToResponseFunction(RestTemplate restTemplate, @Value("${person.management.url}") String personManagementUrl) {
        this.restTemplate = restTemplate;
        this.personManagementUrl = personManagementUrl;
    }

    @Override
    public GetCarResponse apply(Car car) {
        try {
            String url = personManagementUrl + "/" + car.getOwnerId();
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

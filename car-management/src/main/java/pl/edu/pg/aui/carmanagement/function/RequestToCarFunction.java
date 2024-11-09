package pl.edu.pg.aui.carmanagement.function;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.aui.carmanagement.dto.PutCarRequest;
import pl.edu.pg.aui.carmanagement.model.Car;
import pl.edu.pg.aui.carmanagement.model.Person;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToCarFunction implements BiFunction<UUID, PutCarRequest, Car> {

    private final RestTemplate restTemplate;

    public RequestToCarFunction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Car apply(UUID id, PutCarRequest putCarRequest) {
        try {
            String url = "http://localhost:8080/persons/" + putCarRequest.getOwner();
            restTemplate.getForObject(url, Void.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new IllegalArgumentException("Owner not found");
            }
            else {
                throw e;
            }
        }

        return Car.builder()
                .id(id)
                .brand(putCarRequest.getBrand())
                .model(putCarRequest.getModel())
                .power(putCarRequest.getPower())
                .productionYear(putCarRequest.getProductionYear())
                .plate(putCarRequest.getPlate())
                .ownerId(putCarRequest.getOwner())
                .build();
    }
}

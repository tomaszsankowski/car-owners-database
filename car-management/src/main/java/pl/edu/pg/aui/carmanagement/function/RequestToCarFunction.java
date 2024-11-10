package pl.edu.pg.aui.carmanagement.function;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.aui.carmanagement.dto.PutCarRequest;
import pl.edu.pg.aui.carmanagement.model.Car;

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
            String url = "${person.management.url}" + putCarRequest.getOwner();
            restTemplate.getForObject(url, Void.class);
            return Car.builder()
                    .id(id)
                    .brand(putCarRequest.getBrand())
                    .model(putCarRequest.getModel())
                    .power(putCarRequest.getPower())
                    .productionYear(putCarRequest.getProductionYear())
                    .plate(putCarRequest.getPlate())
                    .ownerId(putCarRequest.getOwner())
                    .build();
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Owner not found");
        }
    }
}


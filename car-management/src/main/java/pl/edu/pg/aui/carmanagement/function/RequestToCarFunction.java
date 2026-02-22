package pl.edu.pg.aui.carmanagement.function;

import org.springframework.beans.factory.annotation.Value;
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

    private final String personManagementUrl;

    public RequestToCarFunction(RestTemplate restTemplate, @Value("${person.management.url}") String personManagementUrl) {
        this.restTemplate = restTemplate;
        this.personManagementUrl = personManagementUrl;
    }

    @Override
    public Car apply(UUID id, PutCarRequest putCarRequest) {
        try {
            String url = personManagementUrl + "/" + putCarRequest.getOwner();
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
package pl.edu.pg.aui.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${aui.personmanagement.url}")
            String personUrl,
            @Value("${aui.carmanagement.url}")
            String carUrl,
            @Value("${aui.gateway.host}")
            String host
    ) {
        return builder
                .routes()
                .route("persons", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/persons/{uuid}",
                                "/api/persons"
                        )
                        .uri(personUrl)
                )
                .route("cars", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/cars",
                                "/api/cars/**",
                                "/api/persons/{uuid}/cars",
                                "/api/persons/{uuid}/cars/**"
                        )
                        .uri(carUrl)
                )
                .build();
    }
}
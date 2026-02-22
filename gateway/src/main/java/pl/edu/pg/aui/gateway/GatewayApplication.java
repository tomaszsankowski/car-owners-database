package pl.edu.pg.aui.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}
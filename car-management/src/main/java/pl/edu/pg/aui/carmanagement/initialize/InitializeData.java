package pl.edu.pg.aui.carmanagement.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.carmanagement.model.Car;
import pl.edu.pg.aui.carmanagement.service.api.CarService;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final CarService carService;

    @Autowired
    public InitializeData(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Car car1 = Car.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .brand("Audi")
                .model("A4")
                .productionYear(2010)
                .power(150)
                .plate("GDA12345")
                .ownerId(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .build();
        Car car2 = Car.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .brand("BMW")
                .model("X5")
                .productionYear(2015)
                .power(200)
                .plate("GDA54321")
                .ownerId(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .build();
        Car car3 = Car.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .brand("Mercedes")
                .model("E220")
                .productionYear(2018)
                .power(170)
                .plate("GDA67890")
                .ownerId(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .build();
        Car car4 = Car.builder()
                .id(UUID.randomUUID())
                .brand("Volkswagen")
                .model("Passat")
                .productionYear(2012)
                .power(140)
                .plate("GDA09876")
                .ownerId(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .build();

        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        carService.create(car4);

    }
}

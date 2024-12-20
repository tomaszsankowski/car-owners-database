package pl.edu.pg.aui.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.service.api.CarService;
import pl.edu.pg.aui.service.api.PersonService;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {

    private final CarService carService;

    private final PersonService personService;

    @Autowired
    public InitializeData(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (personService.find("Admin", "Admin").isEmpty()) {

            Person person1 = Person.builder()
                    .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                    .name("Admin")
                    .surname("Admin")
                    .age(99)
                    .build();
            Person person2 = Person.builder()
                    .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                    .name("Jan")
                    .surname("Kowalski")
                    .age(30)
                    .build();
            Person person3 = Person.builder()
                    .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                    .name("Anna")
                    .surname("Nowak")
                    .age(25)
                    .build();

            personService.create(person1);
            personService.create(person2);
            personService.create(person3);

            Car car1 = Car.builder()
                    .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                    .brand("Audi")
                    .model("A4")
                    .productionYear(2010)
                    .power(150)
                    .plate("GDA12345")
                    .owner(person1)
                    .build();
            Car car2 = Car.builder()
                    .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                    .brand("BMW")
                    .model("X5")
                    .productionYear(2015)
                    .power(200)
                    .plate("GDA54321")
                    .owner(person2)
                    .build();
            Car car3 = Car.builder()
                    .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                    .brand("Mercedes")
                    .model("E220")
                    .productionYear(2018)
                    .power(170)
                    .plate("GDA67890")
                    .owner(person1)
                    .build();
            Car car4 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("Volkswagen")
                    .model("Passat")
                    .productionYear(2012)
                    .power(140)
                    .plate("GDA09876")
                    .owner(person2)
                    .build();

            carService.create(car1);
            carService.create(car2);
            carService.create(car3);
            carService.create(car4);
        }

    }
}

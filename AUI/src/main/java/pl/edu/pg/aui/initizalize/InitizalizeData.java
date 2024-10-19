package pl.edu.pg.aui.initizalize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.service.api.CarService;
import pl.edu.pg.aui.service.api.PersonService;

import java.util.UUID;

@Component
public class InitizalizeData implements InitializingBean {

    private final CarService carService;

    private final PersonService personService;

    @Autowired
    public InitizalizeData(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (personService.find("Admin", "Admin").isEmpty()) {

            Person person0 = Person.builder()
                    .id(UUID.randomUUID())
                    .name("Admin")
                    .surname("Admin")
                    .age(100)
                    .build();
            Person person1 = Person.builder()
                    .id(UUID.randomUUID())
                    .name("Jan")
                    .surname("Kowalski")
                    .age(30)
                    .build();
            Person person2 = Person.builder()
                    .id(UUID.randomUUID())
                    .name("Anna")
                    .surname("Nowak")
                    .age(25)
                    .build();
            Person person3 = Person.builder()
                    .id(UUID.randomUUID())
                    .name("Piotr")
                    .surname("Wiśniewski")
                    .age(35)
                    .build();

            personService.create(person0);
            personService.create(person1);
            personService.create(person2);
            personService.create(person3);

            Car car1 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("Audi")
                    .model("A4")
                    .productionYear(2010)
                    .power(150)
                    .plate("GDA12345")
                    .owner(person1)
                    .build();
            Car car2 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("BMW")
                    .model("X5")
                    .productionYear(2015)
                    .power(200)
                    .plate("GDA54321")
                    .owner(person2)
                    .build();
            Car car3 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("Mercedes")
                    .model("E220")
                    .productionYear(2018)
                    .power(170)
                    .plate("GDA67890")
                    .owner(person3)
                    .build();
            Car car4 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("Volkswagen")
                    .model("Passat")
                    .productionYear(2012)
                    .power(140)
                    .plate("GDA09876")
                    .owner(person1)
                    .build();
            Car car5 = Car.builder()
                    .id(UUID.randomUUID())
                    .brand("Toyota")
                    .model("Corolla")
                    .productionYear(2017)
                    .power(130)
                    .plate("GDA65432")
                    .owner(person2)
                    .build();

            carService.create(car1);
            carService.create(car2);
            carService.create(car3);
            carService.create(car4);
            carService.create(car5);
        }

    }
}

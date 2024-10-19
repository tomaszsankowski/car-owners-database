package pl.edu.pg.aui.cmd;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;
import pl.edu.pg.aui.service.api.CarService;
import pl.edu.pg.aui.service.api.PersonService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {

    private final CarService carService;

    private final PersonService personService;

    private final ObjectWriter writer;

    @Autowired
    public ApplicationCommand(CarService carService, PersonService personService, ObjectWriter writer) {
        this.carService = carService;
        this.personService = personService;
        this.writer = writer;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        main_loop:
        while (true) {
            System.out.println("Type 'help' to see available commands.");
            System.out.println("Enter command: ");
            command = scanner.nextLine();
            switch (command) {
                case "help" -> {
                    System.out.println("Available commands:");
                    System.out.println("get_persons - get all persons");
                    System.out.println("get_person - get person by id");
                    System.out.println("delete_person - delete person by id");
                    System.out.println("put_person - add new person");
                    System.out.println("get_cars - get all cars");
                    System.out.println("get_person_cars - get all cars of person");
                    System.out.println("get_car - get car by id");
                    System.out.println("delete_car - delete car by id");
                    System.out.println("put_car - add new car");
                    System.out.println("quit - exit application");
                }
                case "get_persons" -> {
                    System.out.println(writer.writeValueAsString(personService.findAll()));
                }
                case "get_person" -> {
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        Optional<Person> person = personService.find(uuid);
                        if (person.isPresent()) {
                            System.out.println(writer.writeValueAsString(person.get()));
                        } else {
                            System.out.println("NOT_FOUND");
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid UUID format");
                    } catch (Exception ex) {
                        System.out.println("An error occurred: " + ex.getMessage());
                    }
                }
                case "delete_person" -> {
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        personService.delete(uuid);
                    } catch (NoSuchElementException ex) {
                        System.out.println("NOT_FOUND");
                    }
                }
                case "put_person" -> {
                    try {
                        System.out.print("Enter person name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter person surname: ");
                        String surname = scanner.nextLine();
                        System.out.print("Enter person age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        Person person = Person.builder()
                                .id(UUID.randomUUID())
                                .name(name)
                                .surname(surname)
                                .age(age)
                                .build();
                        personService.create(person);
                        System.out.println("Person added successfully.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Bad Request");
                    }
                }
                case "get_cars" -> {
                    System.out.println(writer.writeValueAsString(carService.findAll()));
                }
                case "get_person_cars" -> {
                    UUID uuid = UUID.fromString(scanner.next());
                    try {
                        System.out.println(writer.writeValueAsString(carService.findAll(uuid)));
                    } catch (NoSuchElementException ex) {
                        System.out.println("NOT_FOUND");
                    }
                }
                case "get_car" -> {
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        Optional<Car> car = carService.find(uuid);
                        if (car.isPresent()) {
                            System.out.println(writer.writeValueAsString(car.get()));
                        } else {
                            System.out.println("NOT_FOUND");
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid UUID format");
                    } catch (Exception ex) {
                        System.out.println("An error occurred: " + ex.getMessage());
                    }
                }
                case "delete_car" -> {
                    try {
                        UUID uuid = UUID.fromString(scanner.next());
                        carService.delete(uuid);
                    } catch (NoSuchElementException ex) {
                        System.out.println("NOT_FOUND");
                    }
                }
                case "put_car" -> {
                    try {
                        System.out.print("Enter car brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter car model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter car year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter car power: ");
                        int power = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter car plate: ");
                        String plate = scanner.nextLine();

                        System.out.print("Enter owner name: ");
                        String ownerName = scanner.nextLine();
                        System.out.print("Enter owner surname: ");
                        String ownerSurname = scanner.nextLine();

                        Optional<Person> owner = personService.find(ownerName, ownerSurname);
                        if (owner.isPresent()) {
                            Car car = Car.builder()
                                    .id(UUID.randomUUID())
                                    .brand(brand)
                                    .model(model)
                                    .productionYear(year)
                                    .power(power)
                                    .plate(plate)
                                    .owner(owner.get())
                                    .build();
                            carService.create(car);
                            System.out.println("Car added successfully.");
                        } else {
                            System.out.println("Owner not found. Car not added.");
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Bad Request");
                    }
                }
                case "quit" -> {
                    break main_loop;
                }
            }
        }
    }
}

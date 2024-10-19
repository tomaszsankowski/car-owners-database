package pl.edu.pg.aui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pg.aui.dto.CarDTO;
import pl.edu.pg.aui.entity.Car;
import pl.edu.pg.aui.entity.Person;
import pl.edu.pg.aui.serialization.SerializationUtility;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SpringBootApplication
public class AuiApplication {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// SpringApplication.run(AuiApplication.class, args);

		// 2 : starting data, creating collection and printing it with nested lambdas

		Person person1 = Person.builder()
				.name("Jan")
				.surname("Kowalski")
				.age(30)
				.build();
		Person person2 = Person.builder()
				.name("Anna")
				.surname("Nowak")
				.age(25)
				.build();
		Person person3 = Person.builder()
				.name("Piotr")
				.surname("Wiśniewski")
				.age(35)
				.build();

		Car car1 = Car.builder()
				.brand("Audi")
				.model("A4")
				.year(2010)
				.power(150)
				.plate("GDA12345")
				.owner(person1)
				.build();
		Car car2 = Car.builder()
				.brand("BMW")
				.model("X5")
				.year(2015)
				.power(200)
				.plate("GDA54321")
				.owner(person2)
				.build();
		Car car3 = Car.builder()
				.brand("Mercedes")
				.model("E220")
				.year(2018)
				.power(170)
				.plate("GDA67890")
				.owner(person3)
				.build();
		Car car4 = Car.builder()
				.brand("Volkswagen")
				.model("Passat")
				.year(2012)
				.power(140)
				.plate("GDA09876")
				.owner(person1)
				.build();
		Car car5 = Car.builder()
				.brand("Toyota")
				.model("Corolla")
				.year(2017)
				.power(130)
				.plate("GDA65432")
				.owner(person2)
				.build();

		person1.getCars().add(car1);
		person1.getCars().add(car4);
		person2.getCars().add(car2);
		person2.getCars().add(car5);
		person3.getCars().add(car3);

		List<Person> persons = List.of(person1, person2, person3);

		persons.forEach(prof -> {
			System.out.println(prof.getName() + " " + prof.getSurname() + " (" + prof.getAge() + ")");
			prof.getCars().forEach(car -> {
				System.out.println("\t" + car.getBrand() + " " + car.getModel() + " (" + car.getYear() + ")");
			});
		});

		System.out.println();

		// 3 : create set of all Cars using Stream API pipeline

		Set<Car> cars = persons.stream()
				.flatMap(person -> person.getCars().stream())
				.collect(Collectors.toSet());

		cars.stream().forEach(System.out::println);

		System.out.println();

		// 4 : using stream filter elements and sort it

		cars.stream()
				.filter(car -> car.getPower() > 150)
				.sorted((car1_, car2_) -> car1_.getPower() - car2_.getPower())
				.forEach(System.out::println);

		System.out.println();

		// 5 : pipeline to transform Cars into CarDTO

		List<CarDTO> carDTOs = cars.stream()
				.map(car -> CarDTO.builder()
						.brand(car.getBrand())
						.model(car.getModel())
						.year(car.getYear())
						.power(car.getPower())
						.plate(car.getPlate())
						.ownerName(car.getOwner().getName())
						.ownerSurname(car.getOwner().getSurname())
						.build())
				.sorted()
				.collect(Collectors.toList());

		carDTOs.stream().forEach(System.out::println);

		System.out.println();

		// 6 : serialize and deserialize CarDTOs

		SerializationUtility.serialize(carDTOs, "cars.dat");

		List<CarDTO> deserializedCars = SerializationUtility.deserialize("cars.dat");

		deserializedCars.stream().forEach(System.out::println);

		System.out.println();

		// 7 : forkJoinPool example

		int poolSize = 4;

		ForkJoinPool pool = new ForkJoinPool(poolSize);

		try {
			pool.submit(() -> {
				persons.parallelStream().forEach(person -> {
					try {
						System.out.println("Thread: " + Thread.currentThread().getName() + " " + person.getName() + " " + person.getSurname());
						Thread.sleep(1000);
						person.getCars().parallelStream().forEach(car -> {
							try {
								System.out.println("Thread: " + Thread.currentThread().getName() + " " + car.getBrand() + " " + car.getModel());
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
								throw new RuntimeException(e);
							}
						});
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						throw new RuntimeException(e);
					}
				});
			}).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}

}

package pl.edu.pg.aui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.aui.model.Car;
import pl.edu.pg.aui.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByPlate(String plate);

    Optional<Car> findByBrandAndModel(String brand, String model);

    List<Car> findAllByOwner(Person owner);

    List<Car> findAllByOwnerId(UUID ownerId);

    List<Car> findAllByOwnerNameAndOwnerSurname(String name, String surname);
}

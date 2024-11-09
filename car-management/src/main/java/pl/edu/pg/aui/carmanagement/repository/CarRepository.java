package pl.edu.pg.aui.carmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.aui.carmanagement.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByPlate(String plate);

    Optional<Car> findByBrandAndModel(String brand, String model);

    List<Car> findAllByOwnerId(UUID owner);
}

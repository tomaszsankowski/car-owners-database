package pl.edu.pg.aui.carmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "ownerId")
@EqualsAndHashCode(exclude = "ownerId")
@Entity
@Table(name = "cars")
public class Car implements Serializable, Comparable<Car> {
    @Id
    private UUID id;

    private String brand;

    private String model;

    @Column(name = "production_year")
    private int productionYear;

    private int power;

    private String plate;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Override
    public int compareTo(Car other) {
        return this.plate.compareTo(other.plate);
    }
}

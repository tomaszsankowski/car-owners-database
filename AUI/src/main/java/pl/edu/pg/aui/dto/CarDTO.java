package pl.edu.pg.aui.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CarDTO implements Serializable, Comparable<CarDTO> {
    String brand;

    String model;

    int year;

    int power;

    String plate;

    String ownerName;

    String ownerSurname;

    @Override
    public int compareTo(CarDTO other) {
        return this.plate.compareTo(other.plate);
    }
}

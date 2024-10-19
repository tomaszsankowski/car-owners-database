package pl.edu.pg.aui.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
public class Car implements Serializable, Comparable<Car> {
    String brand;

    String model;

    int year;

    int power;

    String plate;

    Person owner;

    @Override
    public int compareTo(Car other) {
        return this.plate.compareTo(other.plate);
    }
}

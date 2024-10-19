package pl.edu.pg.aui.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.aui.dto.CarDTO;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "cars")
public class Person implements Serializable, Comparable<Person> {
    String name;

    String surname;

    int age;

    @Builder.Default
    List<Car> cars = new ArrayList<>();

    @Override
    public int compareTo(Person other) {
        int surnameComparison = this.surname.compareTo(other.surname);
        if (surnameComparison != 0) {
            return surnameComparison;
        }
        return this.name.compareTo(other.name);
    }
}

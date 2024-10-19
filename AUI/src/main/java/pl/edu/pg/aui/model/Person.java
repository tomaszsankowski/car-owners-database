package pl.edu.pg.aui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cars")
@EqualsAndHashCode(exclude = "cars")
@Entity
@Table(name = "persons")
public class Person implements Serializable, Comparable<Person> {
    @Id
    private UUID id;

    private String name;

    private String surname;

    private  int age;

    @Builder.Default
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Car> cars = new ArrayList<>();

    @Override
    public int compareTo(Person other) {
        int surnameComparison = this.surname.compareTo(other.surname);
        if (surnameComparison != 0) {
            return surnameComparison;
        }
        return this.name.compareTo(other.name);
    }
}

package pl.edu.pg.aui.personmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "persons")
public class Person implements Serializable, Comparable<Person> {
    @Id
    private UUID id;

    private String name;

    private String surname;

    private  int age;

    @Override
    public int compareTo(Person other) {
        int surnameComparison = this.surname.compareTo(other.surname);
        if (surnameComparison != 0) {
            return surnameComparison;
        }
        return this.name.compareTo(other.name);
    }
}

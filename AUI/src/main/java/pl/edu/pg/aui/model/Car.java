package pl.edu.pg.aui.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "owner")
@EqualsAndHashCode(exclude = "owner")
@Entity
@Table(name = "cars")
public class Car implements Serializable, Comparable<Car> {
    @Id
    private UUID id;

    private String brand;

    private String model;

    @Column(name = "`year`")
    private int year;

    private int power;

    private String plate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Person owner;

    @Override
    public int compareTo(Car other) {
        return this.plate.compareTo(other.plate);
    }
}

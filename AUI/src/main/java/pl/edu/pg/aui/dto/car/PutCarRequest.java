package pl.edu.pg.aui.dto.car;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutCarRequest {

    private String brand;

    private String model;

    private int power;

    private int productionYear;

    private String plate;

    private UUID owner;
}

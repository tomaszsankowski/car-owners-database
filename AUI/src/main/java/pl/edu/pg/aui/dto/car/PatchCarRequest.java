package pl.edu.pg.aui.dto.car;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchCarRequest {

    private String brand;

    private String model;

    private int power;

    private int productionYear;

}

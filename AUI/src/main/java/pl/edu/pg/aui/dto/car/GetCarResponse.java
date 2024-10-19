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
public class GetCarResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Person {

        private UUID id;

        private String name;

        private String surname;

    }

    private UUID id;

    private String brand;

    private String model;

    private int power;

    private int productionYear;

    private String plate;

    private Person owner;

}

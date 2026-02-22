package pl.edu.pg.aui.personmanagement.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPersonResponse {

        private UUID id;

        private String name;

        private String surname;

        private int age;

}

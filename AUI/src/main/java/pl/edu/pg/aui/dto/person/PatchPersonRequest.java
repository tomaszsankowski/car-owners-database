package pl.edu.pg.aui.dto.person;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchPersonRequest {
    private String name;

    private String surname;

    private int age;

}

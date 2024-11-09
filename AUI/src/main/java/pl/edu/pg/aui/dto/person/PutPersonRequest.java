package pl.edu.pg.aui.dto.person;

import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPersonRequest {

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 20, message = "Name must be between 1 and 20 characters")
    private String name;

    @NotNull(message = "Surname is mandatory")
    @NotBlank(message = "Surname is mandatory")
    @Size(min = 1, max = 20, message = "Surname must be between 1 and 20 characters")
    private String surname;

    @NotNull(message = "Age is mandatory")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 2000, message = "Age must be at most 100")
    private int age;

}

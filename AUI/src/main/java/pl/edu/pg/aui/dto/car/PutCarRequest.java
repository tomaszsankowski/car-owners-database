package pl.edu.pg.aui.dto.car;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Validated
public class PutCarRequest {

    @NotNull(message = "Brand is mandatory")
    @NotBlank(message = "Brand is mandatory")
    @Size(min = 1, max = 20, message = "Brand must be between 1 and 20 characters")
    private String brand;

    @NotNull(message = "Model is mandatory")
    @NotBlank(message = "Model is mandatory")
    @Size(min = 1, max = 20, message = "Model must be between 1 and 20 characters")
    private String model;

    @NotNull(message = "Production year is mandatory")
    @Min(value = 1900, message = "Production year must be at least 1900")
    @Max(value = 2100, message = "Production year must be at most 2100")
    private int productionYear;

    @NotNull(message = "Power is mandatory")
    @Min(value = 1, message = "Power must be at least 1")
    @Max(value = 2000, message = "Power must be at most 2000")
    private int power;

    @NotNull(message = "Plate is mandatory")
    @NotBlank(message = "Plate is mandatory")
    @Pattern(regexp = "^[A-Z0-9-]{5,10}$", message = "Plate must be between 5 and 10 alphanumeric characters")
    private String plate;

    @NotNull(message = "Owner is mandatory")
    private UUID owner;

}

package lk.ijse.helloshoeshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OccasionDTO implements SuperDTO{
    @NotNull(message = "Occasion code should not null")
    private String occasionCode;
    @NotNull(message = "Occasion Description should not null")
    private String occasionDesc;
}

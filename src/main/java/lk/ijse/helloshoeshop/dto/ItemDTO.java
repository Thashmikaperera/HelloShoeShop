package lk.ijse.helloshoeshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lk.ijse.helloshoeshop.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO{
    @Null(message = "Item code generate by the program")
    private String itemCode;
    @NotBlank(message = "Item Description cannot be blank")
    private String itemDesc;
    @NotNull(message = "Propic cannot be null")
    private String pic;
    @NotNull(message = "Status cannot be null")
    private Status status;
    @NotNull(message = "Category cannot be null")
    private String category;
}

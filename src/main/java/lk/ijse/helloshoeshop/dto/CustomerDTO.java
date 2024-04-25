package lk.ijse.helloshoeshop.dto;

import jakarta.validation.constraints.*;
import lk.ijse.helloshoeshop.entity.enumeratedData.Gender;
import lk.ijse.helloshoeshop.entity.enumeratedData.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO{
    @Null(message = "Customer Id generate by the program")
    private String customerId;

    @NotBlank(message = "customer name cannot be blank")
    @Size(min = 2,max = 50,message = "customer name must bebetween 2 and 50 charactors")
    private String customerName;

    @NotNull(message = "gender cannot be null")
    private Gender gender;

    private Level level;

    @NotNull(message = "Join date cannot be null")
    @PastOrPresent(message = "Join date must be positive oe Zero")
    private Date joinDate;

    @PositiveOrZero(message = "Total points must be positive or Zero")
    private Integer totalPoint;

    @Past(message = "Date of Birth should be in the past")
    @NotNull(message = "Date of Birth cannot be null")
    private Date dob;

    @NotBlank(message = "Address 1 cannot be blank")
    private String address1;

    @NotBlank(message = "Address 2 cannot be blank")
    private String address2;

    @NotBlank(message = "Address 3 cannot be blank")
    private String address3;

    @NotBlank(message = "Address 4 cannot be blank")
    private String address4;

    @NotBlank(message = "Postal code cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$",message = "Invalid cantact number format")
    private String contactNo;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Recent purchased date cannot be null")
    private Timestamp recentPurchasedDate;
}

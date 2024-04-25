package lk.ijse.helloshoeshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lk.ijse.helloshoeshop.Enum.Role;

public class UserDTO implements SuperDTO{
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Role cannot be null")
    private Role role;
}

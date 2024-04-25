package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.entity.enumeratedData.Role;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}

package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.Enum.Category;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "supplier")
@Data
public class SupplierEntity {
    @Id
    private String supplierCode;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postalCode;
    private String country;
    private String contactNo1;
    private String contactNo2;
    private String email;
    @OneToMany(mappedBy = "supplierEntity",cascade = CascadeType.ALL)
    private List<StockEntity> stockEntities;
}

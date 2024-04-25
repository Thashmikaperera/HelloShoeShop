package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "size")
public class SizeEntity {
    @Id
    private String sizeCode;
    private String sizeDesc;
    @OneToMany(mappedBy = "sizeEntity",cascade = CascadeType.ALL)
    private List<StockSizeEntity> stockSizeEntities;
}

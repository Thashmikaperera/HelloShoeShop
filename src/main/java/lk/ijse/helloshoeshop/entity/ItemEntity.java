package lk.ijse.helloshoeshop.entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "item")
@Data
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemDesc;
    private String pic;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "genderCode",nullable = false)
    private GenderEntity genderEntity;

    @ManyToOne
    @JoinColumn(name = "occasionCode",nullable = false)
    private OccasionEntity occasionEntity;

    @ManyToOne
    @JoinColumn(name = "varietyCode",nullable = false)
    private VarietyEntity varietyEntity;

    @OneToMany(mappedBy = "itemEntity",cascade = CascadeType.ALL)
    private List<StockEntity> stockEntities;
}

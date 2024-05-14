package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.Enum.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "item")public class ItemEntity {
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

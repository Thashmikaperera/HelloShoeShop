package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "variety")
public class VarietyEntity {
    @Id
    private String varietyCode;
    private String varietyDesc;
    @OneToMany(mappedBy = "varietyEntity",cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;
}

package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "returns")
@Data
public class ReturnEntity {
    @Id
    private String returnId;
    private Timestamp returnDate;
    @OneToOne(cascade = CascadeType.ALL)
    private StockSizeOrderDetailsEntity stockSizeOrderDetailsEntity;
    private int qty;
}

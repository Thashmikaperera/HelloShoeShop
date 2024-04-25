package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stockSizeOrderDetails")
public class StockSizeOrderDetailsEntity {
    @Id
    private String stockSizeOrderDetailsEntity;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "stockId",nullable = false)
    private StockSizeEntity stockSizeEntity;

    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private OrderEntity orderEntity;
}

package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.Enum.PaymentMethod;

import java.security.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String orderNo;
    private Timestamp purchasedDate;
    private int addedPoints;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "cutomerId",nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "email",nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    private List<StockSizeOrderDetailsEntity> stockSizeOrderDetailsEntities ;
}

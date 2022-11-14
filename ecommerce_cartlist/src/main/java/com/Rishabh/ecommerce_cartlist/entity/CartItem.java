package com.Rishabh.ecommerce_cartlist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Cart_Item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Long id;
    @Column
    private String productId;
    @Column
    private float cartDiscount;
    @Column
    private float productPrice;
    @Column
    private int quantity;
    @Column
    private String supplierName;
    @Column
    private float deliveryFees;
    @Column
    private float totalPrice;
    @Column
    private LocalDateTime createdOn;
    @Column
    private LocalDateTime updatedOn;

}

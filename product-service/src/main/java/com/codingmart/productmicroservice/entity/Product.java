package com.codingmart.productmicroservice.entity;

import com.codingmart.productmicroservice.audit.Auditable;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product extends Auditable<String> {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Product name should be provided")
    private String name;
    @NotNull(message = "Price of the product should be provided")
    private Double price;
    private Double maximumRetailPrice; // price + all taxes
    private Double finalDiscountedPrice; // price - discounts
    @PositiveOrZero(message = "quantity must not be negative")
    private Long quantity;
    //doubt
    private String code;
    private String color;
    private String description;
    @NotNull(message = "ChildCategoryId should be provided")
    private Long childCategoryId;
    @NotNull(message = "SupplierId should be provided")
    private Long supplierId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @NotNull(message = "Brand should be provided")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @NotNull(message = "Provide active status")
    private Boolean active;

    @ManyToMany
    @JoinTable(
            name = "product_tax_mapping",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tax_id"))
    @ToString.Exclude
    private List<Tax> taxes;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @NotNull(message = "size details should be provided")
    List<Size> availableSizes;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    List<Image> images;

    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    @ToString.Exclude
    @NotNull(message = "Product Type should be provided")
    Type type;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.codingmart.productmicroservice.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_type")
public class Type extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Type name should be provided")
    @Column(unique = true)
    private String name;
    @NotNull(message = "Provide active status")
    private Boolean active;


}

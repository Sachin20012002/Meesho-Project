package com.codingmart.productmicroservice.entity;


import lombok.*;
import org.hibernate.validator.constraints.URL;

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
public class Image extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Image name should be provided")
    @Column(unique = true)
    private String name;
    @URL(message = "Provide valid URL")
    @NotBlank(message = "URL should be provided")
    private String url;
    @NotNull(message = "Provide active status")
    private Boolean active;

}
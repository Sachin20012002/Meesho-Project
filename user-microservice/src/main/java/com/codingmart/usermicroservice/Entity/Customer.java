package com.codingmart.usermicroservice.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    long id;
    String name;

    public Customer(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


}

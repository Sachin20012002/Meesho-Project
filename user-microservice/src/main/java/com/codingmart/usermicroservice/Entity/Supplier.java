package com.codingmart.usermicroservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Supplier")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier
{

        @Id
        @GeneratedValue
        long id;
        @Column(length=60)
        String password;
        long gstDetails;
        @OneToOne(cascade=CascadeType.ALL)
        private Bank bank;


}


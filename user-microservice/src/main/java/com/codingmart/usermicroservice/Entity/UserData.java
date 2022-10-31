package com.codingmart.usermicroservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO:AUDIT ENTITY LISTENER
public class UserData extends Auditable
{
        @Id
        @GeneratedValue
        long id;
        long mobileNo;
        String emailId;
        String Role;
//         boolean isSupplier;
        boolean enabled = false;
        @OneToOne(cascade = CascadeType.ALL)
        public Customer customer;
        @OneToOne(cascade = CascadeType.ALL)
        public Supplier supplier;
        @OneToOne(cascade = CascadeType.ALL)
        public Address address;

}



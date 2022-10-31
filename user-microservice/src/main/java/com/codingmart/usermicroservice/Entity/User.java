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
public class User
{
        @Id
        @GeneratedValue
        long id;
        long mobileNo;
        String emailId;
//        boolean isSupplier;
        boolean enabled = false;
        @OneToOne(cascade = CascadeType.ALL)
        public Customer customer;
        @OneToOne(cascade = CascadeType.ALL)
        public Supplier supplier;
        @OneToOne(cascade = CascadeType.ALL)
        public Address address;

        public User(long id, long mobileNo, String emailId,boolean enabled) {
            super();
            this.id = id;
            this.mobileNo = mobileNo;
            this.emailId = emailId;
//            this.isSupplier = isSupplier;
            this.enabled = enabled;
        }


}



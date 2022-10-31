package com.codingmart.usermicroservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Address")
@Data
@NoArgsConstructor
public class Address {
        @Id
        @GeneratedValue
        private long id;
        private String area;
        private String town;
        private String state;
        private String country;
        private long pinCode;

    public Address(long id, String area, String town, String state, String country, long pinCode) {
            this.id = id;
            this.area = area;
            this.town = town;
            this.state = state;
            this.country = country;
            this.pinCode = pinCode;
        }
    }

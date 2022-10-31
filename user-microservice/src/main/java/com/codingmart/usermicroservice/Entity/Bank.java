package com.codingmart.usermicroservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@NoArgsConstructor
@Data
public class Bank {
        @Id
        @GeneratedValue
        long id;
        long accountno;
        int ifsccode;
        @OneToOne(cascade = CascadeType.ALL)
        private Address address;

        public Bank(long id, long accountno, int ifsccode)
        {
            this.id = id;
            this.accountno = accountno;
            this.ifsccode = ifsccode;
        }


    }

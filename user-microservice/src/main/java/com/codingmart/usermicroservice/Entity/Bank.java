package com.codingmart.usermicroservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@NoArgsConstructor
@Data
public class Bank  {
        @Id
        @GeneratedValue
        long id;
        long accountNo;
        int ifscCode;
        @OneToOne(cascade = CascadeType.ALL)
        private Address address;

        public Bank(long id, long accountNo, int ifscCode)
        {
            this.id = id;
            this.accountNo = accountNo;
            this.ifscCode = ifscCode;
        }


    }

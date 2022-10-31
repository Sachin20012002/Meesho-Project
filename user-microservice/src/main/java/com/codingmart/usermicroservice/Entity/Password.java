package com.codingmart.usermicroservice.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Password {
    @Id
    @GeneratedValue
    String emailId;
    String oldPassword;
    String newPassword;
}

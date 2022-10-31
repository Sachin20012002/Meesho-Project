package com.codingmart.usermicroservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="token")
@Data
@NoArgsConstructor
public class VerificationToken{

                private static final int EXPIRATION_TIME=10;
                @Id
                @GeneratedValue
                private long id;
                private String token;
                private Date expirationTime;

                @OneToOne(cascade= CascadeType.ALL)
                private User user;

                public VerificationToken(String token) {
                        super();
                        this.token = token;
                        this.expirationTime=calculateExpirationDate(EXPIRATION_TIME);
                }
                public VerificationToken( String token,User user) {
                        super();
                        this.token = token;
                        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
                        this.user = user;
                }

                private Date calculateExpirationDate(int expirationTime) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(new Date().getTime());
                        calendar.add(Calendar.MINUTE, expirationTime);
                        return new Date(calendar.getTime().getTime());
                }

        }


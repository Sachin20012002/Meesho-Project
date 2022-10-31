package com.codingmart.usermicroservice.Repository;

import com.codingmart.usermicroservice.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long>
{
    PasswordResetToken findByToken(String token);
}

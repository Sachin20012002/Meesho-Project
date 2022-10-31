package com.codingmart.usermicroservice.Repository;

import com.codingmart.usermicroservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailId(String emailId);
}

package com.codingmart.usermicroservice.Repository;

import com.codingmart.usermicroservice.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData,Long> {
    UserData findByEmailId(String emailId);
}

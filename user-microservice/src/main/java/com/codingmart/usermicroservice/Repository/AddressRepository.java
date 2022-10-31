package com.codingmart.usermicroservice.Repository;

import com.codingmart.usermicroservice.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>
{
}

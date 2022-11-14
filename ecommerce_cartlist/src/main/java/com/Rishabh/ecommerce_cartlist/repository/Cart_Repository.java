package com.Rishabh.ecommerce_cartlist.repository;

import com.Rishabh.ecommerce_cartlist.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_Repository extends JpaRepository<Cart,Long> {
}

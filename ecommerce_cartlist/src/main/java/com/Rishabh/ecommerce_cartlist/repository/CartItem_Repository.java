package com.Rishabh.ecommerce_cartlist.repository;

import com.Rishabh.ecommerce_cartlist.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItem_Repository extends JpaRepository<CartItem,Long> {

}

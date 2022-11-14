package com.Rishabh.ecommerce_cartlist.repository;

import com.Rishabh.ecommerce_cartlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishList_Repository extends JpaRepository<WishList,Long> {
}

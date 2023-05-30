package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart WHERE customer_id= ?1 ",nativeQuery = true)
    public void deleteCart(int customerId);
}

package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM orders WHERE customer_id= ?1 ",nativeQuery = true)
    public void removeItemFromData(int customerId);
}

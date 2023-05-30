package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Entity.Item;
import com.amazon.in.EcommerceApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    public Item findByProduct(Product product);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM item WHERE cart_id= ?1 ",nativeQuery = true)
    public void removeItemFromData(int cartId);
}

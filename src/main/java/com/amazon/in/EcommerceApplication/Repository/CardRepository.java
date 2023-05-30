package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    public Card findByCardNo(String cardNo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM card WHERE customer_id= ?1 ",nativeQuery = true)
   public void deleteAllCards(int customerId);
}


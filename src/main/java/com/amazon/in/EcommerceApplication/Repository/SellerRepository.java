package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByPanNo(String panNo);

    public List<Seller> findByAge(int age);
}

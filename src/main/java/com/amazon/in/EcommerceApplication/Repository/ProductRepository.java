package com.amazon.in.EcommerceApplication.Repository;

import com.amazon.in.EcommerceApplication.Enum.ProductCategory;
import com.amazon.in.EcommerceApplication.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findByProductCategory(ProductCategory productCategory);
}

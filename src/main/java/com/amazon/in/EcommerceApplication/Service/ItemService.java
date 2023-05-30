package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Entity.Product;
import com.amazon.in.EcommerceApplication.Exception.ProductNotExistException;
import com.amazon.in.EcommerceApplication.Repository.ProductRepository;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotExistException {
        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotExistException("Sorry, Invalid product id.");
        }
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                //.quantityRequired(0)
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .name(product.getName())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}

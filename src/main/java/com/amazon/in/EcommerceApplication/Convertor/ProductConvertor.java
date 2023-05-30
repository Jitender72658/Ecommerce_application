package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Product;
import com.amazon.in.EcommerceApplication.Repository.SellerRepository;
import com.amazon.in.EcommerceApplication.RequestDto.ProductRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductConvertor {
   @Autowired
   SellerRepository sellerRepository;
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .productCategory(productRequestDto.getProductCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .build();
        return product;
    }
    public static ProductResponseDto productToProductResponseDto(Product product){
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .price(product.getPrice())
                .name(product.getName())
                .sellerName(product.getSeller().getName())
                .productStatus(product.getProductStatus())
                .productCategory(product.getProductCategory())
                .build();
        return productResponseDto;
    }
}

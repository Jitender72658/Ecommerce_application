package com.amazon.in.EcommerceApplication.Service;
import com.amazon.in.EcommerceApplication.Convertor.ProductConvertor;
import com.amazon.in.EcommerceApplication.Enum.ProductCategory;
import com.amazon.in.EcommerceApplication.Enum.ProductStatus;
import com.amazon.in.EcommerceApplication.Repository.ProductRepository;
import com.amazon.in.EcommerceApplication.Entity.Product;
import com.amazon.in.EcommerceApplication.Entity.Seller;
import com.amazon.in.EcommerceApplication.Exception.ProductCategoryNotExistException;
import com.amazon.in.EcommerceApplication.Exception.SellerNotExistException;
import com.amazon.in.EcommerceApplication.Repository.SellerRepository;
import com.amazon.in.EcommerceApplication.RequestDto.ProductRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ProductResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotExistException {
        Seller seller;
        try {
           seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotExistException("Seller doesn't exist. First register as seller and then add products.");
        }
        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        if(productRequestDto.getQuantity()>0){
            product.setProductStatus(ProductStatus.AVAILABLE);
        }
        else {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        seller.getProductList().add(product);
        sellerRepository.save(seller);
        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
        return productResponseDto;
    }
    public List<ProductResponseDto> getByProductCategory(ProductCategory productCategory) throws ProductCategoryNotExistException {

        List<Product> products = new ArrayList<>();
         try{
                   products = productRepository.findByProductCategory(productCategory);
             }
         catch (Exception e){
             throw new ProductCategoryNotExistException("Sorry, the category is not valid. Please try again entering the right category.");
         }
        List<ProductResponseDto> productResponseDtos = new ArrayList<>() ;
        for(Product product: products){
            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}

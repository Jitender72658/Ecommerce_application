package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.Enum.ProductCategory;
import com.amazon.in.EcommerceApplication.ResponseDto.ProductResponseDto;
import com.amazon.in.EcommerceApplication.Service.ProductService;
import com.amazon.in.EcommerceApplication.Exception.ProductCategoryNotExistException;
import com.amazon.in.EcommerceApplication.Exception.SellerNotExistException;
import com.amazon.in.EcommerceApplication.RequestDto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotExistException {
      ProductResponseDto productResponseDto;
       try{
           productResponseDto =  productService.addProduct(productRequestDto);
       }
       catch(Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getby/{category}")
    public ResponseEntity getByCategory(@PathVariable("category") ProductCategory productCategory) throws ProductCategoryNotExistException {
        List<ProductResponseDto> products;
          try{
               products = productService.getByProductCategory(productCategory);
          }
          catch (Exception e){
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity(products,HttpStatus.ACCEPTED);
    }

}

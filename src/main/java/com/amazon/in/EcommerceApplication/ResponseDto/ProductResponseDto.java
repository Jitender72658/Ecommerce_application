package com.amazon.in.EcommerceApplication.ResponseDto;

import com.amazon.in.EcommerceApplication.Enum.ProductCategory;
import com.amazon.in.EcommerceApplication.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private int price;
    private String name;
    private String sellerName;

    private ProductStatus productStatus;
    private ProductCategory productCategory;

}

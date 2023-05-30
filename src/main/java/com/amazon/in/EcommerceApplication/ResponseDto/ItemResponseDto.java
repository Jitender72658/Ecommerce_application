package com.amazon.in.EcommerceApplication.ResponseDto;

import com.amazon.in.EcommerceApplication.Enum.ProductCategory;
import com.amazon.in.EcommerceApplication.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDto {
    private String name;
    private int quantityRequired;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}

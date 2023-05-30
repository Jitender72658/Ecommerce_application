package com.amazon.in.EcommerceApplication.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SellerRequestDto {
    private int age;
    private String name;
    private String mobNo;
     private String email;
     private String panNo;
}

package com.amazon.in.EcommerceApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponseDto {
    private String name;
    private int age;
    private String mobNo;
}

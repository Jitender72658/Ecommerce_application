package com.amazon.in.EcommerceApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerResponseDto {
    private String name;
    private String mobNo;

    private String email;
    private String panNo;

}

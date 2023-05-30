package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Seller;
import com.amazon.in.EcommerceApplication.RequestDto.SellerRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.SellerResponseDto;

public class SellerConvertor {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        Seller seller = Seller.builder()
                .age(sellerRequestDto.getAge())
                .name(sellerRequestDto.getName())
                .mobNo(sellerRequestDto.getMobNo())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .build();
        return seller;
    }
    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
         SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                 .name(seller.getName())
                 .mobNo(seller.getMobNo())
                 .email(seller.getEmail())
                 .panNo(seller.getPanNo())
                 .build();
         return sellerResponseDto;
    }
}

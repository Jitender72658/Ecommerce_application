package com.amazon.in.EcommerceApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartCheckoutResponseDto {

    private List<ItemResponseDto> itemList;

    private int cartTotal;

    private String cardUsedForPayment;



}

package com.amazon.in.EcommerceApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private List<ItemResponseDto> itemList;

    private Date orderDate;

    private int totalCost;

    private int deliveryCharge;

    private String cardUsedForPayment;
}

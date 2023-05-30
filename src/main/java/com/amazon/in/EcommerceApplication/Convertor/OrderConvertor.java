package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Orders;
import com.amazon.in.EcommerceApplication.ResponseDto.OrderResponseDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;

import java.util.List;

public class OrderConvertor {

    public static OrderResponseDto orderToOrderResponseDto(Orders order){
        List<ItemResponseDto> itemResponseDtoList = ItemConvertor.itemListToItemResponseDtoList(order.getItems());
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .totalCost(order.getTotalAmount())
                .orderDate(order.getOrderDate())
                .itemList(itemResponseDtoList)
                .deliveryCharge(order.getDeliveryCharges())
                .cardUsedForPayment(order.getCardUsedForPayment())
                .build();
        return orderResponseDto;
    }
}

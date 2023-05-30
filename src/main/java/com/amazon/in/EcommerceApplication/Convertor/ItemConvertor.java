package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Item;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ItemConvertor {

    public static List<ItemResponseDto> itemListToItemResponseDtoList(List<Item> listItem){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item: listItem){
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .quantityRequired(item.getRequiredQuantity())
                    .price(item.getProduct().getPrice())
                    .name(item.getProduct().getName())
                    .productCategory(item.getProduct().getProductCategory())
                    .productStatus(item.getProduct().getProductStatus())
                    .build();
            itemResponseDtos.add(itemResponseDto);
        }
         return itemResponseDtos;
    }
}

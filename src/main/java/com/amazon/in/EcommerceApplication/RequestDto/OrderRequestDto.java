package com.amazon.in.EcommerceApplication.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private int productId;

    private int customerId;

    private int requiredQuantity;
}

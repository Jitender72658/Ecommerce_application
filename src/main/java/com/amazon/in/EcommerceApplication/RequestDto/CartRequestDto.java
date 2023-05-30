package com.amazon.in.EcommerceApplication.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequestDto{
    private int productId;

    private int customerId;

    private int requiredQuantity;
}

package com.amazon.in.EcommerceApplication.RequestDto;

import com.amazon.in.EcommerceApplication.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CardRequestDto {
    private String cardNo;
    private String cvv;
    private CardType cardType;
    private int customerId;
}

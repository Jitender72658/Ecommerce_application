package com.amazon.in.EcommerceApplication.ResponseDto;

import com.amazon.in.EcommerceApplication.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardResponseDto {
    private String cardNo;
    private CardType cardType;
}

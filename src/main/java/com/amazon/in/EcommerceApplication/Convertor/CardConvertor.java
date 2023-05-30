package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Card;
import com.amazon.in.EcommerceApplication.RequestDto.CardRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CardResponseDto;

public class CardConvertor {
    public  static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){

        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
        return card;
    }
    public static CardResponseDto CardToCardResponseDto(Card card){
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++) {
            cardNo += 'X';
        }
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);

        CardResponseDto cardResponseDto = CardResponseDto.builder()
                .cardType(card.getCardType())
                .cardNo(cardNo)
                .build();
        return cardResponseDto;
    }
}

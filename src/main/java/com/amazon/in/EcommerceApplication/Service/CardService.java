package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Exception.CardNotExistException;
import com.amazon.in.EcommerceApplication.Convertor.CardConvertor;
import com.amazon.in.EcommerceApplication.Entity.Card;
import com.amazon.in.EcommerceApplication.Entity.Customer;
import com.amazon.in.EcommerceApplication.Exception.CustomerNotExistException;
import com.amazon.in.EcommerceApplication.Repository.CardRepository;
import com.amazon.in.EcommerceApplication.Repository.CustomerRepository;
import com.amazon.in.EcommerceApplication.RequestDto.CardRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotExistException{
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }
       Card card = CardConvertor.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);
        CardResponseDto cardResponseDto = CardConvertor.CardToCardResponseDto(card);
     //   customerRepository.save(customer);

        return cardResponseDto;
    }

    public String deleteCard(CardRequestDto cardRequestDto) throws CustomerNotExistException, CardNotExistException {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }
        Card card =cardRepository.findByCardNo(cardRequestDto.getCardNo());;
        if(card==null){
            throw new CardNotExistException("Sorry, Invalid card number.");
        }

        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++) {
            cardNo += 'X';
        }
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);

        cardRepository.deleteById(card.getId());
        return "Card "+cardNo+" has been deleted successfully.";
    }
    public List<CardResponseDto> getAllCards(int customerId) throws CustomerNotExistException{
        Customer customer ;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }
        List<Card> cards = customer.getCards();
        List<CardResponseDto> cardResponseDtos = new ArrayList<>();
        for(Card card: cards){
            CardResponseDto cardResponseDto = CardConvertor.CardToCardResponseDto(card);
            cardResponseDtos.add(cardResponseDto);
        }
        return cardResponseDtos;
    }

    public String deleteAllCardsByCustomerId(int customerId) throws CustomerNotExistException{
        Customer customer ;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }

       cardRepository.deleteAllCards(customerId);
        return "All cards of the customer has been removed successfully.";
    }
}

package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.Service.CardService;
import com.amazon.in.EcommerceApplication.RequestDto.CardRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    /// adding a card in database
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        CardResponseDto cardResponseDto;
        try{
            cardResponseDto = cardService.addCard(cardRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDto,HttpStatus.ACCEPTED);
    }

    //// delete a card by cardNo;
    @PostMapping("/delete")
    public ResponseEntity deleteCard(@RequestBody CardRequestDto cardRequestDto){
        String message;
        try{
            message = cardService.deleteCard(cardRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }



    /// get all cards of a particular customerId
    @GetMapping("/getAllCards")
    public ResponseEntity getAllCards(@RequestParam int customerId){
        List<CardResponseDto> cardResponseDtos;
        try{
            cardResponseDtos = cardService.getAllCards(customerId);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cardResponseDtos,HttpStatus.ACCEPTED);
    }

    // deleting all the card of a particular customer by customerId
    @DeleteMapping("/delete-all-cards")
    public ResponseEntity deleteAllCardsByCustomerId(@RequestParam int customerId){
        String message;
        try{
            message = cardService.deleteAllCardsByCustomerId(customerId);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }
}

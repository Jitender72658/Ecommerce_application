package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.ResponseDto.CartCheckoutResponseDto;
import com.amazon.in.EcommerceApplication.RequestDto.CartRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;
import com.amazon.in.EcommerceApplication.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add-item")
    public ResponseEntity addItemToCart(@RequestBody CartRequestDto cartRequestDto){
        List<ItemResponseDto> itemList;
        try {
            itemList = cartService.addItemToCart(cartRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(itemList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestParam("customerId") int customerId){
        CartCheckoutResponseDto cartCheckoutResponseDto;
        try {
            cartCheckoutResponseDto = cartService.checkoutCart(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cartCheckoutResponseDto,HttpStatus.ACCEPTED);
    }
}

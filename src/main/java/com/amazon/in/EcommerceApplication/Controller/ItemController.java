package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.Exception.ProductNotExistException;
import com.amazon.in.EcommerceApplication.Repository.ItemRepository;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;
import com.amazon.in.EcommerceApplication.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/view/{productId}")
    public ResponseEntity viewItem(@PathVariable("productId") int productId){

        ItemResponseDto itemResponseDto;
        try{
            itemResponseDto = itemService.viewItem(productId);
        } catch (ProductNotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDto,HttpStatus.ACCEPTED);
    }
}

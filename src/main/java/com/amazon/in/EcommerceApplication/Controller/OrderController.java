package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.RequestDto.OrderRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.OrderResponseDto;
import com.amazon.in.EcommerceApplication.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderResponseDto orderResponseDto;
        try{
            orderResponseDto = orderService.placeOrder(orderRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity getAllOrdersOfACustomer(@RequestParam("customerId") int customerId){
        List<OrderResponseDto> orderResponseDtoList ;
        try{
            orderResponseDtoList = orderService.getAllOrdersOfACustomer(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(orderResponseDtoList,HttpStatus.ACCEPTED);
    }
}

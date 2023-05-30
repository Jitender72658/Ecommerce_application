package com.amazon.in.EcommerceApplication.Controller;
import com.amazon.in.EcommerceApplication.Service.CustomerService;
import com.amazon.in.EcommerceApplication.RequestDto.CustomerRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }
    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam int customerId){
        CustomerResponseDto customerResponseDto;
        try{
            customerResponseDto = customerService.getById(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public List<CustomerResponseDto> getAll(){
       return customerService.getAll();
    }

    @GetMapping("/getByEmail")
    public ResponseEntity getByEmail(@RequestParam String email){
        CustomerResponseDto customerResponseDto;
        try{
            customerResponseDto = customerService.getByEmail(email);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customerResponseDto,HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteById(@RequestParam int customerId){
        String message;
        try{
            message = customerService.deleteById(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }

    // delete customer by id

    // update customer
}

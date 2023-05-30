package com.amazon.in.EcommerceApplication.Controller;

import com.amazon.in.EcommerceApplication.ResponseDto.SellerResponseDto;
import com.amazon.in.EcommerceApplication.Service.SellerService;
import com.amazon.in.EcommerceApplication.Exception.SellerNotExistException;
import com.amazon.in.EcommerceApplication.RequestDto.SellerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public SellerResponseDto addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }


// Get all sellers

// get a seller by PAN Card

// find sellers of a particular age

    @GetMapping("find-by-pan-no")
    public ResponseEntity findByPanNo(@RequestParam("panNo") String panNo){
       SellerResponseDto sellerResponseDto;
       try{
           sellerResponseDto = sellerService.findByPanNo(panNo);
       }
       catch (SellerNotExistException e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity(sellerResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("find-by-age")
    public ResponseEntity findByAge(@RequestParam("age") int age){

        List<SellerResponseDto> sellerResponseDtos ;

        try{
            sellerResponseDtos = sellerService.findByAge(age);
        }
        catch (SellerNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(sellerResponseDtos,HttpStatus.ACCEPTED);
    }

    @GetMapping("get-all")
    public List<SellerResponseDto> getAll() {
        return sellerService.getAll();
    }
}

package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Convertor.SellerConvertor;
import com.amazon.in.EcommerceApplication.ResponseDto.SellerResponseDto;
import com.amazon.in.EcommerceApplication.Entity.Seller;
import com.amazon.in.EcommerceApplication.Exception.SellerNotExistException;
import com.amazon.in.EcommerceApplication.Repository.SellerRepository;
import com.amazon.in.EcommerceApplication.RequestDto.SellerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    JavaMailSender emailSender;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){

        Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);

        String text = "Congrats Seller! your registration is successful.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("111111111@gmail.com");
        message.setTo(seller.getEmail());
        message.setSubject("Registration on ecommerce");
        message.setText(text);
        emailSender.send(message);
        return SellerConvertor.sellerToSellerResponseDto(seller);
    }

    public SellerResponseDto findByPanNo(String panNo) throws SellerNotExistException {
        Seller seller = sellerRepository.findByPanNo(panNo);
        if(seller==null){
            throw  new SellerNotExistException("Sorry, no seller exist with this panNo.");
        }
        SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
        return sellerResponseDto;
    }

    public List<SellerResponseDto> findByAge(int age) throws SellerNotExistException{
        List<Seller> sellers = sellerRepository.findByAge(age);

        if(sellers==null){
            throw  new SellerNotExistException("Sorry, no seller exists with provided age.");
        }
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Seller seller: sellers) {
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }

    public List<SellerResponseDto> getAll(){
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Seller seller: sellers) {
            SellerResponseDto sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }


}

package com.example.FlipCommerceMock.service.Impl;


import com.example.FlipCommerceMock.dto.RequestDto.SellerRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.SellerResponseDto;
import com.example.FlipCommerceMock.model.Seller;
import com.example.FlipCommerceMock.repository.SellerRepository;
import com.example.FlipCommerceMock.service.SellerService;
import com.example.FlipCommerceMock.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){

        // dto -> entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);
        // prepare response Dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}

package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.SellerRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.SellerResponseDto;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}

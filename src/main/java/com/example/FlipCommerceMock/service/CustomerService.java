package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.CustomerRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}

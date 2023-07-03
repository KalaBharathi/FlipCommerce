package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.CardRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CardResponseDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}

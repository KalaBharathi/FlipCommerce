package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.CheckOutCartRequestDto;
import com.example.FlipCommerceMock.dto.RequestDto.ItemRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CartResponseDto;
import com.example.FlipCommerceMock.dto.ResponseDto.OrderResponseDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.exception.EmptyCartException;
import com.example.FlipCommerceMock.exception.InsufficientQuantityException;
import com.example.FlipCommerceMock.exception.InvalidCardException;
import com.example.FlipCommerceMock.model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}

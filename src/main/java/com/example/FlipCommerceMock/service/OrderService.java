package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.OrderRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.OrderResponseDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.exception.InsufficientQuantityException;
import com.example.FlipCommerceMock.exception.InvalidCardException;
import com.example.FlipCommerceMock.exception.ProductNotFoundException;
import com.example.FlipCommerceMock.model.Card;
import com.example.FlipCommerceMock.model.Cart;
import com.example.FlipCommerceMock.model.OrderEntity;

public interface OrderService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}

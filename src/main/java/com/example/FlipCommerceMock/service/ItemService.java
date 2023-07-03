package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.dto.RequestDto.ItemRequestDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.exception.InsufficientQuantityException;
import com.example.FlipCommerceMock.exception.OutOfStockException;
import com.example.FlipCommerceMock.exception.ProductNotFoundException;
import com.example.FlipCommerceMock.model.Item;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}

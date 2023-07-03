package com.example.FlipCommerceMock.service;

import com.example.FlipCommerceMock.Enum.Category;
import com.example.FlipCommerceMock.dto.RequestDto.ProductRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerceMock.exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);
}

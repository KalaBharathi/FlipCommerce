package com.example.FlipCommerceMock.transformer;


import com.example.FlipCommerceMock.dto.ResponseDto.CartResponseDto;
import com.example.FlipCommerceMock.dto.ResponseDto.ItemResponseDto;
import com.example.FlipCommerceMock.model.Cart;
import com.example.FlipCommerceMock.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}

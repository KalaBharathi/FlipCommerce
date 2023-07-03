package com.example.FlipCommerceMock.transformer;


import com.example.FlipCommerceMock.dto.ResponseDto.ItemResponseDto;
import com.example.FlipCommerceMock.model.Customer;
import com.example.FlipCommerceMock.model.Item;
import com.example.FlipCommerceMock.model.Product;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(int quantity){

        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quatityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}


package com.example.FlipCommerceMock.transformer;


import com.example.FlipCommerceMock.dto.RequestDto.OrderRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.ItemResponseDto;
import com.example.FlipCommerceMock.dto.ResponseDto.OrderResponseDto;
import com.example.FlipCommerceMock.model.Customer;
import com.example.FlipCommerceMock.model.Item;
import com.example.FlipCommerceMock.model.OrderEntity;
import jakarta.persistence.criteria.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity OrderRequestDtoToOrder(Item item, Customer customer){

        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtoList)
                .build();
    }
}

package com.example.FlipCommerceMock.dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    int quatityAdded;

    String productName;

    int price;
}

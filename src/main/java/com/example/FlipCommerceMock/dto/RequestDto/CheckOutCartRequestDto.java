package com.example.FlipCommerceMock.dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutCartRequestDto {

    String emailId;

    String cardNo;

    int cvv;
}


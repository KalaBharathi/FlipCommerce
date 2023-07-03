package com.example.FlipCommerceMock.dto.ResponseDto;


import com.example.FlipCommerceMock.Enum.Category;
import com.example.FlipCommerceMock.Enum.ProductStatus;
import com.example.FlipCommerceMock.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    Category category;

    int price;

    ProductStatus productStatus;
}

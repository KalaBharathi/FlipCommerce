package com.example.FlipCommerceMock.transformer;


import com.example.FlipCommerceMock.Enum.ProductStatus;
import com.example.FlipCommerceMock.dto.RequestDto.ProductRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerceMock.model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProducToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}

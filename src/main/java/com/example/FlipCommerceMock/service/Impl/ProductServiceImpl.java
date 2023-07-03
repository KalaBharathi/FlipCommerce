package com.example.FlipCommerceMock.service.Impl;


import com.example.FlipCommerceMock.Enum.Category;
import com.example.FlipCommerceMock.Enum.ProductStatus;
import com.example.FlipCommerceMock.dto.RequestDto.ProductRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerceMock.exception.SellerNotFoundException;
import com.example.FlipCommerceMock.model.Product;
import com.example.FlipCommerceMock.model.Seller;
import com.example.FlipCommerceMock.repository.ProductRepository;
import com.example.FlipCommerceMock.repository.SellerRepository;
import com.example.FlipCommerceMock.service.ProductService;
import com.example.FlipCommerceMock.transformer.ProductTransformer;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller==null){
            throw new SellerNotFoundException("EmailId is not registered");
        }
        // dto to entity
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        seller.getProducts().add(product);
        product.setSeller(seller);


        // save product
        Seller savedSeller = sellerRepository.save(seller); // save both product and seller
        //Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        // prepare response dto
        return ProductTransformer.ProducToProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price){

        List<Product> products = productRepository.findByCategoryAndPrice(category,price);

        // prepare a list of dtos
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProducToProductResponseDto(product));
        }
        return productResponseDtos;
    }
}

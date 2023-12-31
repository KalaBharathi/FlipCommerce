package com.example.FlipCommerceMock.service.Impl;

import com.example.FlipCommerceMock.dto.RequestDto.ItemRequestDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.exception.InsufficientQuantityException;
import com.example.FlipCommerceMock.exception.OutOfStockException;
import com.example.FlipCommerceMock.exception.ProductNotFoundException;
import com.example.FlipCommerceMock.model.Customer;
import com.example.FlipCommerceMock.model.Item;
import com.example.FlipCommerceMock.model.Product;
import com.example.FlipCommerceMock.repository.CustomerRepository;
import com.example.FlipCommerceMock.repository.ProductRepository;
import com.example.FlipCommerceMock.service.ItemService;
import com.example.FlipCommerceMock.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Product product = productOptional.get();
        if(product.getQuantity()==0){
            throw new OutOfStockException("Product is out of stock");
        }
        if(product.getQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not avaiable");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}

package com.example.FlipCommerceMock.service.Impl;

import com.example.FlipCommerceMock.dto.RequestDto.CustomerRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CustomerResponseDto;
import com.example.FlipCommerceMock.model.Cart;
import com.example.FlipCommerceMock.model.Customer;
import com.example.FlipCommerceMock.repository.CustomerRepository;
import com.example.FlipCommerceMock.service.CustomerService;
import com.example.FlipCommerceMock.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  // saves both customer and cart
        // prepare response Dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}


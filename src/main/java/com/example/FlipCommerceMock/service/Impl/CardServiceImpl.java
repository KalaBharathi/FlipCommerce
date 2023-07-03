package com.example.FlipCommerceMock.service.Impl;

import com.example.FlipCommerceMock.dto.RequestDto.CardRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CardResponseDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.model.Card;
import com.example.FlipCommerceMock.model.Customer;
import com.example.FlipCommerceMock.repository.CustomerRepository;
import com.example.FlipCommerceMock.service.CardService;
import com.example.FlipCommerceMock.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid email id!!!");
        }

        // dto -> entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        // save customer and card
        Customer savedCustomer = customerRepository.save(customer);

        // preapre response Dto
        return CardTransformer.CardToCardResponseDto(card);
    }
}

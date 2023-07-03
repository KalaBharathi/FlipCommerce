package com.example.FlipCommerceMock.service.Impl;

import com.example.FlipCommerceMock.dto.RequestDto.CheckOutCartRequestDto;
import com.example.FlipCommerceMock.dto.RequestDto.ItemRequestDto;
import com.example.FlipCommerceMock.dto.ResponseDto.CartResponseDto;
import com.example.FlipCommerceMock.dto.ResponseDto.OrderResponseDto;
import com.example.FlipCommerceMock.exception.CustomerNotFoundException;
import com.example.FlipCommerceMock.exception.EmptyCartException;
import com.example.FlipCommerceMock.exception.InsufficientQuantityException;
import com.example.FlipCommerceMock.exception.InvalidCardException;
import com.example.FlipCommerceMock.model.*;
import com.example.FlipCommerceMock.repository.*;
import com.example.FlipCommerceMock.service.CartService;
import com.example.FlipCommerceMock.service.OrderService;
import com.example.FlipCommerceMock.transformer.CartTransformer;
import com.example.FlipCommerceMock.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
   OrderRepository orderRepository;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart);  // saves both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItems().add(savedItem);
        //prepare response dto
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {
        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! You can't use this card!");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartException("Cart is empty!!");
        }

        try{
            OrderEntity order = orderService.placeOrder(cart,card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            return OrderTransformer.OrderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientQuantityException e){
            throw e;
        }
    }
    private void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems())
            item.setCart(null);
        cart.setItems(new ArrayList<>());
    }
}

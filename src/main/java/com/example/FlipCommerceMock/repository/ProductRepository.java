package com.example.FlipCommerceMock.repository;

import com.example.FlipCommerceMock.Enum.Category;
import com.example.FlipCommerceMock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryAndPrice(Category category, int price);
}

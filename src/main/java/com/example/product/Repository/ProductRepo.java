package com.example.product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	public List<Product> findByPriceGreaterThan(int price);

}

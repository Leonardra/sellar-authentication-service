package com.github.leonardra.sellarauthenticationservice.repository;

import com.github.leonardra.sellarauthenticationservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

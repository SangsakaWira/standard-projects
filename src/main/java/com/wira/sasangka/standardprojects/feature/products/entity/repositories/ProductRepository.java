package com.wira.sasangka.standardprojects.feature.products.entity.repositories;

import com.wira.sasangka.standardprojects.feature.products.entity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
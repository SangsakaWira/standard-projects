package com.wira.sasangka.standardprojects.repository;

import com.wira.sasangka.standardprojects.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
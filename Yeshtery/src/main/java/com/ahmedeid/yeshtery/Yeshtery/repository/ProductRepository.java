package com.ahmedeid.yeshtery.Yeshtery.repository;

import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

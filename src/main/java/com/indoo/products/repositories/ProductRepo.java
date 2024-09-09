package com.indoo.products.repositories;

import com.indoo.products.entities.ProductEntity;
import com.indoo.products.records.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    ProductEntity findProductById(Long id);
}

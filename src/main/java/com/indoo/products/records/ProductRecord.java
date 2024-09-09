package com.indoo.products.records;

import lombok.AllArgsConstructor;

public record ProductRecord(
         Long id,
         String name,
         String description,
         Double price,
         String category,
         Integer quantityInStock) {
}

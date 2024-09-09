package com.indoo.products.controllers;

import com.indoo.products.entities.ProductEntity;
import com.indoo.products.records.ChatRecord;
import com.indoo.products.records.ProductRecord;
import com.indoo.products.records.SustainabilityReview;
import com.indoo.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    public ProductEntity getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @PostMapping("/{productId}/buy")
    public boolean buyProduct(@PathVariable long productId){
        return productService.buyProduct(productId);
    }

    @PostMapping("/chat")
    public String chatProduct(@RequestBody ChatRecord chat){
        return productService.chatProduct(chat.chat());
    }

    @PostMapping("/sustainability-check")
    public SustainabilityReview productReview(@RequestParam(value="product", defaultValue = "Dove") String value){
        return productService.sustainabilityReview(value);
    }
}

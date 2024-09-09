package com.indoo.products.configs;

import com.indoo.products.repositories.ProductRepo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class AIConfig {

    //Make it true, when initializing data in vector.
    boolean vectorInitialization = false;

    @Bean
    TokenTextSplitter tokenTextSplitter(){
        return new TokenTextSplitter();
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, ProductRepo productRepo, VectorStore vectorStore) {
        if(vectorInitialization){
            productRepo.findAll().forEach(product -> {
                var document = new Document(
                        product.getName() +" "+product.getDescription(),
                        Map.of("price", product.getPrice(),
                                "id", product.getId(),
                                "name", product.getName(),
                                "description", product.getDescription())
                );
                var split = tokenTextSplitter().apply(List.of(document));
                vectorStore.add(split);
            });
        }
        return builder
                .defaultAdvisors(
                    new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults())
                )
                .build();
    }
}

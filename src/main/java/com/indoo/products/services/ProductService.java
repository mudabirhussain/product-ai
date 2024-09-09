package com.indoo.products.services;

import com.indoo.products.entities.ProductEntity;
import com.indoo.products.records.ProductRecord;
import com.indoo.products.records.SustainabilityReview;
import com.indoo.products.repositories.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ChatClient chatClient;

    private final ProductRepo productRepo;

    @Value("classpath:/prompts/SustainabilityReviewPrompt.st")
    private Resource PRODUCT_REVIEW_PROMPT;

    public ProductService(ChatClient chatClient, ProductRepo productRepo) {
        this.chatClient = chatClient;
        this.productRepo = productRepo;
    }

    public ProductEntity getProduct(long id) {
         return productRepo.findProductById(id);
    }

    public boolean buyProduct(long productId) {
        return false;
    }

    public String chatProduct(String message){
        var systemMessage = new SystemMessage("Your primary function is the product inquiry, if someone asks for any other type of inquiry, please tell them that you don't know");
        var userMessage = new UserMessage(message);
        return chatClient.prompt(new Prompt(List.of(systemMessage, userMessage)))
                .call()
                .content();
    }

    public SustainabilityReview sustainabilityReview(String product) {
        val outputParser = new BeanOutputParser<>(SustainabilityReview.class);
        String format = outputParser.getFormat();
        val promptTemplate = new PromptTemplate(PRODUCT_REVIEW_PROMPT, Map.of("product", product, "format", format));
        val prompt = promptTemplate.create();
        return outputParser.parse(chatClient.prompt(prompt).call().content());
    }
}

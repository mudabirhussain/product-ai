package com.indoo.products.records;

import java.util.Map;

public record SustainabilityReview(
        String productName,
        String overallSummary,
        Integer overallRating,
        Map<String, String> reviewSummary,
        Map<String, Integer> reviewRating
) {
}

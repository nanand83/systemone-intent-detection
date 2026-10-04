package com.example.intentdetection.model;

/**
 * Represents a high-level business function (e.g., 'Inventory Management', 'Billing').
 * This structure helps categorize the user's intent domain.
 *
 * Since this is an initial scaffold, we use simple records for type safety.
 */
public record BusinessCapability(String value, Double confidenceScore) {
    // Constructor or validation can be added later.
}
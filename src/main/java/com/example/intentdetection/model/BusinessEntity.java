package com.example.intentdetection.model;

/**
 * Represents a concrete object or concept mentioned by the user (e.g., 'Widget-A', 'Billing Cycle').
 */
public record BusinessEntity(String value, Double confidenceScore) {}
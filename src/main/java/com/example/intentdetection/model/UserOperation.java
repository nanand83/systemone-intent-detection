package com.example.intentdetection.model;

/**
 * Represents the action the user wishes to perform (e.g., 'Create', 'Update', 'Query').
 */
public record UserOperation(String value, Double confidenceScore) {}
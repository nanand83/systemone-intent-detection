package com.example.intentdetection.model;

/**
 * The final structured representation of an intent derived from processing raw text,
 * containing the key elements found across the ontology facets.
 */
public record DetectedIntent(
        BusinessCapability capability,
        BusinessEntity entity,
        UserOperation operation
) {}
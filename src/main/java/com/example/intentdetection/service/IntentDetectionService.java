package com.example.intentdetection.service;

import com.example.intentdetection.model.BusinessCapability;
import com.example.intentdetection.model.BusinessEntity;
import com.example.intentdetection.model.DetectedIntent;
import com.example.intentdetection.model.UserOperation;

import org.springaicommunity.typesafe.TypeSafeClient;
import org.springaicommunity.typesafe.question.Choice;
import org.springaicommunity.typesafe.response.SystemOneResponse;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IntentDetectionService {

    private final TypeSafeClient typeSafeClient;

    public IntentDetectionService(TypeSafeClient typeSafeClient) {
        this.typeSafeClient = typeSafeClient;
    }

    public DetectedIntent detectIntent(String userUtterance) {
        
        // 1. Business Capability Facet
        var capabilityChoice = Choice.builder()
            .instructions("Which enterprise risk capability does the request fall under?")
            .option("cyber_it_risk", "Cybersecurity incidents, infrastructure outages, software vulnerabilities, and data leakage")
            .option("fraud_financial_crime", "Internal/external fraud, payment scams, anti-money laundering breakdowns, and unauthorized trading")
            .option("conduct_compliance", "Regulatory breaches, employee misconduct, cross-border policy violations, and ethics reporting")
            .option("third_party_vendor", "Vendor risk assessments, fourth-party supply chain failures, and outsourced service disruptions")
            .option("business_continuity", "Disaster recovery, operational resilience, crisis management, and geographic disruption events")
            .option("control_assurance", "Internal control testing, design evaluation, validation, audit remediation, and effectiveness scoring")
            .option("none", "None of the defined NFR capabilities apply")
            .build();

        // 2. Business Entity Facet
        var entityChoice = Choice.builder()
            .instructions("What is the primary business entity being discussed?")
            .option("application", "Core banking systems, trading platforms, APIs, or internal microservices")
            .option("vendor", "Third-party SaaS providers, market data vendors, or external cloud infrastructure")
            .option("transaction", "Wire transfers, clearing flows, settlement records, or payment instructions")
            .option("control", "Internal audit controls, Key Risk Indicators, or automated security guardrails")
            .option("policy", "Compliance rulebooks, regulatory frameworks, or internal mandates")
            .option("incident", "Active security breaches, operational error tickets, or loss event reports")
            .option("employee", "Trading desk personnel, system administrators, or authorized corporate users")
            .option("customer", "Corporate clients, retail accounts, or counterparty institutional entities")
            .option("report", "Risk dashboards, regulatory filings, or board-level risk summaries")
            .option("none", "None of the defined business entities apply")
            .build();

        // 3. Operation Facet
        var operationChoice = Choice.builder()
            .instructions("What operation or action is the user trying to perform?")
            .option("investigate", "Analyze root causes, search forensic logs, trace data flows, or audit historical activities")
            .option("assess", "Score risk levels, evaluate control effectiveness, or run compliance impact simulations")
            .option("remediate", "Mitigate vulnerabilities, isolate compromised assets, patch systems, or trigger workflow freezes")
            .option("report", "Generate exportable audit trails, summarize metrics, or compile regulatory filing packages")
            .option("configure", "Update risk thresholds, modify policy rules, or change access control parameters")
            .option("none", "None of the specified operations apply")
            .build();

        // Assemble the typed questions
        var questions = Map.of(
            "business_capability", capabilityChoice,
            "business_entity", entityChoice,
            "operation", operationChoice
        );

        // Jev evaluates all three facets against the utterance in one parallel call
        SystemOneResponse response = typeSafeClient.systemOne(userUtterance, questions);

        return new DetectedIntent(
            new BusinessCapability(response.choice("business_capability").value(), response.choice("business_capability").confidence()), 
            new BusinessEntity(response.choice("business_entity").value(), response.choice("business_entity").confidence()),
            new UserOperation(response.choice("operation").value(), response.choice("operation").confidence())
        );
    }
}
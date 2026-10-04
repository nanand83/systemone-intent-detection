# 🛡️ Enterprise Intent Detection Service (Risk Focus)

A specialized, AI-powered Spring Boot application designed for **Enterprise Risk** domains. This service analyzes complex, unstructured user utterances to extract highly structured, actionable intent data using a dedicated System-1 model.

## ✨ Core Technology & Functionality
The system's core intelligence is driven by the proprietary **"Jev" System-1 Model**, optimized for classifying risk-related user queries. It interprets ambiguity within enterprise communication and transforms raw text into codified instructions necessary for compliance, reporting, and immediate action.

*   **System Model:** Jev (System-1)
*   **Domain Focus:** Enterprise Risk Management (ERM), Compliance, Operational Risk.
*   **Functionality:** Converts unstructured user language into structured `BusinessIntent` payloads.

## 📐 Architecture & Components
The service is built on modern Java standards and leverages a layered architecture:

| Component | Path | Description |
| :--- | :--- | :--- |
| **Core Service** | `service/IntentDetectionService.java` | Orchestrates the call to the Jev model, processes the output, and structures the final intent payload. |
| **Application Entry** | `DemoApplication.java` | The main Spring Boot application bootstrap class. |
| **Data Models (`model/*`)** | Model | Define the structured data contracts required for risk analysis: |
| - `BusinessCapability.java` | Model | Defines major risk domains (e.g., `FINANCE_RISK`, `COMPLIANCE`). |
| - `BusinessEntity.java` | Model | Represents specific extracted entities like compliance codes, regulatory bodies, or policy numbers. |
| - `DetectedIntent.java` | Model | The final payload structure capturing the risk intent, including capability, entity context, and required operation. |
| - `UserOperation.java` | Model | Specifies the necessary remedial action (e.g., `INVESTIGATE`, `FLAG_HIGH`, `REPORT`). |

## ⚙️ Getting Started (Development Guide)

Follow these steps to get the project running locally. **Note:** This service requires a modern JDK environment.

### Prerequisites
*   **Java Development Kit (JDK) 25+**
*   Gradle (Recommended: Use `./gradlew`)

### Installation Steps
1.  **Clone the Repository:**
    ```bash
    git clone <repository-url>
    cd intent_detection
    ```
2.  **Build and Run:**
    Execute the standard Spring Boot run task via Gradle:
    ```bash
    ./gradlew bootRun
    ```

### Testing & Validation
To validate both unit tests and integration points (including the model wrapper logic):
```bash
./gradlew test
```

## 💡 Conceptual Usage Example (Risk Query)

The system is designed to ingest verbose, ambiguous risk-related text and output a precise structure:

**Input Utterance:**
> "I'm concerned about potential breaches related to GDPR for our European client accounts last quarter."

**Expected Output Structure:**
```
DetectedIntent[
    capability=BusinessCapability[value=conduct_compliance, confidenceScore=0.71], 
    entity=BusinessEntity[value=customer, confidenceScore=0.7], 
    operation=UserOperation[value=investigate, confidenceScore=0.41]
]
```

---
*Developed for Enterprise Risk Analysis | Version: 2.0.0 | Last Updated: October 4, 2026*
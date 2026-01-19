# Log Analyzer API – CI/CD with Kubernetes

This project demonstrates an end-to-end CI/CD pipeline built using
GitHub Actions, Docker, and Kubernetes, following DevSecOps principles.

---

## Application Overview

Log Analyzer API is a Spring Boot–based REST application that analyzes
plain-text application logs and provides summarized insights such as
error counts and frequent error messages.

### Available Endpoints
- GET /ping – Health check endpoint
- POST /analyze – Analyze log content

---

## How to Run Locally

### Prerequisites
- Java 17
- Maven
- Docker (optional)

### Steps
1. Build the application:
   ./mvnw clean package

2. Run the application:
   java -jar target/log-analyzer-0.0.1-SNAPSHOT.jar

3. Access the application:
   http://localhost:8080/ping

---

## CI/CD Overview

This project uses GitHub Actions to implement a complete CI/CD pipeline
following DevSecOps principles.

### CI Pipeline Includes
- Code checkout and build
- Unit testing
- Code quality checks (Checkstyle)
- Static Application Security Testing (CodeQL)
- Dependency vulnerability scanning (SCA)
- Docker image build
- Container vulnerability scanning (Trivy)
- Runtime container smoke testing
- Secure Docker image push to DockerHub

### CD Pipeline
- Deploys the trusted Docker image to Kubernetes
- Uses declarative Deployment and Service manifests
- Exposes the application via NodePort
- Runtime validation using /ping and /analyze endpoints

---

## Kubernetes Deployment

Kubernetes manifests are available in the `k8s/` directory:
- deployment.yaml – Defines the application deployment
- service.yaml – Exposes the application using NodePort

---

## Secrets Configuration

The following secrets are configured using GitHub Actions Secrets:

| Secret Name | Purpose |
|------------|--------|
| DOCKERHUB_USERNAME | DockerHub username |
| DOCKERHUB_TOKEN | Secure Docker registry access token |

No secrets are hardcoded in the repository.

---

## CI Explanation

The CI pipeline enforces quality and security gates before deployment.
Each stage must pass successfully before the artifact is promoted to
the next stage, ensuring reliable and secure software delivery.
